package teapot;

import teapot.utils.Console;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

public class Client {
    public static final Client instance = new Client();

    private Client() {
        // Private constructor to prevent instantiation
    }

    public void run(int port) {
        try {
            InetSocketAddress hostAddress = new InetSocketAddress(InetAddress.getLocalHost(), port);
            SocketChannel client = SocketChannel.open(hostAddress);
            Console.instance.sayHello();
            Console.instance.run(client);
            client.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
