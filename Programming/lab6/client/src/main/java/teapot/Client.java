package teapot;

import teapot.utils.Console;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.channels.SocketChannel;

public class Client {
    public static final Client instance = new Client();

    private Client() {
        // Private constructor to prevent instantiation
    }

    public void run(int port) {
        try {
            InetSocketAddress hostAddress = new InetSocketAddress(InetAddress.getLocalHost(), port);
            Console.instance.sayHello();
            Console.instance.run(hostAddress);
        } catch (InterruptedException e) {
            System.out.println("Client interrupted");
        } catch (UnknownHostException e) {
            System.out.println("Cannot use localhost as a host");
        } catch (IOException e) {
            System.out.println("Channel operations unavailable for now.\nCheck server status.");
            Console.instance.close();
        }

    }
}
