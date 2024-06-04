package teapot.utils;

import teapot.models.CommandRequest;
import teapot.models.Response;
import teapot.utils.enums.ResponseCode;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public final class Console {
    public static final Console instance = new Console();

    private final Scanner in = new Scanner(System.in);
    private SocketChannel channel;
    private InetSocketAddress currentHostAddress;
    private boolean running;

    private Console() {
        // Private constructor to prevent instantiation
    }

    public void sayHello() {
        System.out.println(
                """

                                            __            __     __            __  __     __
                        .----..-----..----.|  |--..-----.|  |_  |  |--..--.--.|__||  |.--|  |.-----..----.
                        |   _||  _  ||  __||    < |  -__||   _| |  _  ||  |  ||  ||  ||  _  ||  -__||   _|
                        |__|  |_____||____||__|__||_____||____| |_____||_____||__||__||_____||_____||__|


                                                            by Gleb Kiva


                                        Type (help) to list all command descriptions
                                        and parameter types they accept. Parameters
                                        should be entered on the same line as command,
                                        separated by space.
                        """);
    }

    public void run(InetSocketAddress hostAddress) throws InterruptedException, IOException {
        connect(hostAddress, 0);
        running = true;
        while (running) {
            System.out.print("\nType any command: ");
            String[] userCommand = in.nextLine().toLowerCase().split("\\s+");
            CommandRequest request = getCommandRequest(
                    userCommand[0],
                    Arrays.copyOfRange(userCommand, 1, userCommand.length));
            try {
                printResponse(request);
            } catch (IOException e) {
                System.out.println("Connection lost. Trying to reconnect...");
                connect(hostAddress, 0);
                System.out.println("Connection established");
            }
        }
    }

    public void connect(InetSocketAddress hostAddress, int tryNumber) throws IOException {
        if (tryNumber > 10) throw new IOException("Unable to access the server");
        try {
            channel = SocketChannel.open(hostAddress);
            this.currentHostAddress = hostAddress;
        } catch (IOException e) {
            connect(hostAddress, ++tryNumber);
        }
    }

    public CommandRequest getCommandRequest(String abbreviation, String... args) {
        CommandRequest c = new CommandRequest(abbreviation, args);
        if (Objects.equals(c.abbreviation(), "exit")) {
            close();
            return null;
        }
        return c;
    }

    public Response sendRequest(CommandRequest request) throws IOException {
        Response response = new Response().setCode(ResponseCode.NOT_RECEIVED);
        ByteBuffer buffer = ByteBuffer.allocate(10000);
        buffer.put(Serializer.toBytes(request));
        buffer.flip();
        channel.write(buffer);
        buffer.clear();
        channel.read(buffer);
        buffer.flip();
        byte[] data = new byte[buffer.remaining()];
        buffer.get(data);
        response = Serializer.fromBytes(data, Response.class);
        return response;
    }

    public void printResponse(CommandRequest request) throws IOException {
        try {
            Response response = sendRequest(request);
            if (response.getCode() == ResponseCode.SUCCESS) {
                response.getData().forEach(System.out::println);
            } else {
                System.out.printf("Server responded with an error\nResponse code: %s\n", response.getCode());
                if (!response.getData().isEmpty()) response.getData().forEach(System.out::println);
            }
        } catch (IOException e) {
            System.out.println("Connection lost. Trying to reconnect...");
            connect(currentHostAddress, 0);
            System.out.println("Connection established");
            printResponse(request);
        }

    }

    public void close() {
        running = false;
    }
}
