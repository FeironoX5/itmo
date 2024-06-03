package teapot;

import teapot.utils.RequirementHandler;

import java.net.InetAddress;

public class App {
    private App() {
        // Private constructor to prevent instantiation
    }

    public static void main(String... args) {
        try {
            int port = RequirementHandler.requirePositive(
                    RequirementHandler.requireInteger(args[0])
            );
            Client.instance.run(port);
        } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
            System.err.println("Port not specified");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
