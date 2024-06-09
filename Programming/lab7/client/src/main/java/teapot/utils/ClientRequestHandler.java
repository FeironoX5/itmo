package teapot.utils;

import teapot.models.CommandRequest;
import teapot.models.Response;
import teapot.utils.enums.ResponseCode;
import teapot.utils.handlers.RequestHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ClientRequestHandler implements RequestHandler {
    private SocketChannel channel;
    private InetSocketAddress currentHostAddress;

    public ClientRequestHandler(InetSocketAddress hostAddress) throws InterruptedException {
        connect(hostAddress, 0);
    }

    @Override
    public void sayHello() {
        System.out.println(
                """

                                            __            __     __            __  __     __
                        .----..-----..----.|  |--..-----.|  |_  |  |--..--.--.|__||  |.--|  |.-----..----.
                        |   _||  _  ||  __||    < |  -__||   _| |  _  ||  |  ||  ||  ||  _  ||  -__||   _|
                        |__|  |_____||____||__|__||_____||____| |_____||_____||__||__||_____||_____||__|


                                                            by Gleb Kiva


                                        Type (helpc) to list all command descriptions
                                        and parameter types they accept. Parameters
                                        should be entered on the same line as command,
                                        separated by space.
                        """);
    }

    @Override
    public Response process(CommandRequest request) throws InterruptedException {
        try {
            return sendRequest(request);
        } catch (IOException e) {
            System.out.println("Connection lost. Trying to reconnect...");
            connect(currentHostAddress, 0);
            System.out.println("Connection established");
            return process(request);
        }
    }

    public void connect(InetSocketAddress hostAddress, int tryNumber) throws InterruptedException {
        if (tryNumber > 10) throw new InterruptedException("Unable to access the server");
        try {
            channel = SocketChannel.open(hostAddress);
            this.currentHostAddress = hostAddress;
        } catch (IOException e) {
            connect(hostAddress, ++tryNumber);
        }
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
}
