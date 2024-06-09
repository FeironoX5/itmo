package teapot;

import teapot.utils.ClientRequestHandler;
import teapot.utils.Console;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

public class Client {
    public static final Client instance = new Client();
    private final Console console;

    private Client() {
        console = new Console();
    }

    public void run(int port) {
        try {
            InetSocketAddress hostAddress = new InetSocketAddress(InetAddress.getLocalHost(), port);
            ClientRequestHandler clientRequestHandler = new ClientRequestHandler(hostAddress);
            clientRequestHandler.sayHello();
            console.run(clientRequestHandler);
        } catch (InterruptedException e) {
            System.out.println("Client interrupted");
        } catch (UnknownHostException e) {
            System.out.println("Cannot use localhost as a host");
        }
    }
}
