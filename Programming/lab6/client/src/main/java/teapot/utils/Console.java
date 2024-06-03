package teapot.utils;

import teapot.models.CommandRequest;
import teapot.models.Response;
import teapot.utils.enums.ResponseCode;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public final class Console {
    public static final Console instance = new Console();

    private final Scanner in = new Scanner(System.in);
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

    public void run(SocketChannel channel) throws IOException, InterruptedException {
        running = true;
        while (running) {
            System.out.print("\nType any command: ");
            String[] userCommand = in.nextLine().toLowerCase().split("\\s+");
            CommandRequest request = getCommandRequest(
                    userCommand[0],
                    Arrays.copyOfRange(userCommand, 1, userCommand.length));
            Response response = sendRequest(request, channel);
            if (response.getCode() == ResponseCode.SUCCESS) {
                response.getData().forEach(System.out::println);
            } else {
                response.getData().forEach(System.err::println);
            }
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

    public Response sendRequest(CommandRequest request, SocketChannel channel) {
        Response response = new Response().setCode(ResponseCode.NOT_RECEIVED);
        try {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public void close() {
        running = false;
    }
}
