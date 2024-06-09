package teapot.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.*;

/*
 * осуществляет выполнение команд по управлению коллекцией
 * ракет по запросу клиента, а также управляет портами
 */
public class Server {
    public static final Server instance = new Server();
    private final HashMap<Integer, Thread> portListeners;
    public final Logger logger;
    /*
     * Осуществляет логирование различных этапов работы
     * сервера (начало работы, получение нового подключения,
     * получение нового запроса, отправка ответа и т.п.)
     */
    final Scanner in = new Scanner(System.in);

    private Server() {
        portListeners = new HashMap<>();
        logger = LoggerFactory.getLogger("server");
    }

    public void listenPort(int port, CommandManager commandsManager) {
        portListeners.put(port, new Thread(createPortListener(port, commandsManager)));
        portListeners.get(port).start();
    }


    private Runnable createPortListener(int port, CommandManager commandsManager) {
        return new Runnable() {
            @Override
            public void run() {
                try {
                    Selector selector = Selector.open();
                    ServerSocketChannel serverChannel = ServerSocketChannel.open();
                    serverChannel.configureBlocking(false);

                    InetSocketAddress hostAddress = new InetSocketAddress(InetAddress.getLocalHost(), port);
                    serverChannel.bind(hostAddress);

                    serverChannel.register(selector, SelectionKey.OP_ACCEPT);
                    while (true) {
                        handleRequest(selector);
                    }
                } catch (IOException e) {
                    System.err.printf("Error while initializing the listener on port %d", port);
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
                System.out.println("Connected to: " + remoteAddr);
                channel.register(selector, SelectionKey.OP_READ);
            }

            private void read(SelectionKey key) throws IOException {
                SocketChannel channel = (SocketChannel) key.channel();
                ByteBuffer buffer = ByteBuffer.allocate(10000);
                int r = channel.read(buffer);
                if (r == -1) {
                    Socket socket = channel.socket();
                    SocketAddress remoteAddr = socket.getRemoteSocketAddress();
                    System.out.println("Connection closed by client: " + remoteAddr);
                    channel.close();
                    key.cancel();
                    return;
                }

                byte[] data = new byte[r];
                System.arraycopy(buffer.array(), 0, data, 0, r);
                CommandRequest commandRequest = Objects.requireNonNull(Serializer.fromBytes(data, CommandRequest.class));
                Response response = commandsManager.runCommand(
                        commandRequest.abbreviation(),
                        commandRequest.args());
                buffer.clear();
                buffer.put(Serializer.toBytes(response));
                buffer.flip();
                channel.write(buffer);
            }
        };
    }

    public void closePortListener(int port) {
        portListeners.get(port).interrupt();
    }
}
