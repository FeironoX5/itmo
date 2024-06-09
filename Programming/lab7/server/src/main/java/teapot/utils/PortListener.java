package teapot.utils;

import teapot.ServerRunner;
import teapot.models.CommandRequest;
import teapot.models.Response;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PortListener implements Runnable {
    private static final int BUFFER_SIZE = 10000;
    private static final int MAX_RECEIVING_THREADS = 10;
    private final int PORT;
    private final CommandManager commandsManager;
    private final ExecutorService receivePool = Executors.newFixedThreadPool(MAX_RECEIVING_THREADS);
    private final ExecutorService sendPool = Executors.newCachedThreadPool();


    public PortListener(int port, CommandManager commandsManager) {
        this.PORT = port;
        this.commandsManager = commandsManager;
    }

    @Override
    public void run() {
        ServerRunner.logger.info(String.format("Server listens for port %s", PORT));
        try {
            Selector selector = Selector.open();
            ServerSocketChannel serverChannel = ServerSocketChannel.open();
            serverChannel.configureBlocking(false);

            InetSocketAddress hostAddress = new InetSocketAddress(InetAddress.getLocalHost(), PORT);
            serverChannel.bind(hostAddress);

            serverChannel.register(selector, SelectionKey.OP_ACCEPT);
            while (!Thread.currentThread().isInterrupted()) {
                handleRequest(selector);
            }
        } catch (IOException e) {
            String message = String.format("Error while initializing the listener on port %d", PORT);
            ServerRunner.logger.error(message);
        }
    }

    private void handleRequest(Selector selector) throws IOException {
        int readyCount = selector.select();
        if (readyCount == 0) {
            return;
        }
        Set<SelectionKey> readyKeys = selector.selectedKeys();
        Iterator<SelectionKey> iterator = readyKeys.iterator();
        while (iterator.hasNext()) {
            SelectionKey key = iterator.next();
            iterator.remove();
            if (!key.isValid()) {
                continue;
            }
            if (key.isAcceptable()) {
                // клиент хочет подключится
                this.accept(key, selector);
            } else if (key.isReadable()) {
                // готово соединение сервер < клиент
                this.read(key);
            }
        }
    }

    private void accept(SelectionKey key, Selector selector) throws IOException {
        ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
        SocketChannel channel = serverChannel.accept();
        channel.configureBlocking(false);
        Socket socket = channel.socket();
        SocketAddress remoteAddr = socket.getRemoteSocketAddress();
        ServerRunner.logger.info(String.format("Connected to: " + remoteAddr));
        channel.register(selector, SelectionKey.OP_READ);
    }

    private void read(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        ServerRunner.logger.info(String.format("Buffer with capacity %s created, position: %s", buffer.capacity(), buffer.position()));
        int r = channel.read(buffer);
        ServerRunner.logger.info(String.format("Data read from channel to buffer, position: %s", buffer.position()));
        if (r == -1) {
            Socket socket = channel.socket();
            SocketAddress remoteAddr = socket.getRemoteSocketAddress();
            ServerRunner.logger.info(String.format("Connection closed by client: " + remoteAddr));
            channel.close();
            key.cancel();
            return;
        }
        byte[] data = new byte[r];
        System.arraycopy(buffer.array(), 0, data, 0, r);
        ServerRunner.logger.info(String.format("Parsing data (%s bytes)", data.length));
        CommandRequest commandRequest = Objects.requireNonNull(Serializer.fromBytes(data, CommandRequest.class));
        ServerRunner.logger.info(String.format("Executing command %s", commandRequest.abbreviation()));
        Response response = commandsManager.runCommand(
                commandRequest.abbreviation(),
                commandRequest.args());
        ServerRunner.logger.info(String.format("Response with code %s received", response.getCode()));
        buffer.clear();
        ServerRunner.logger.info(String.format("Buffer cleared, position: %s", buffer.position()));
        buffer.put(Serializer.toBytes(response));
        ServerRunner.logger.info(String.format("Elements loaded, position: %s", buffer.position()));
        buffer.flip();
        ServerRunner.logger.info(String.format("Buffer flipped, position: %s", buffer.position()));
        channel.write(buffer);
    }

}
