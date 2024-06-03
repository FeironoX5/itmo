package teapot;

import teapot.utils.RequirementHandler;

public class App {
    public static void main(String... args) {
        int port = RequirementHandler.requirePositive(
                RequirementHandler.requireInteger(args[0])
        );
        ServerRunner rocketServerRunner = new ServerRunner("data.json");
        rocketServerRunner.runServer(port);
    }
}