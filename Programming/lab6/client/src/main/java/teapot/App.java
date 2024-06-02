package teapot;

public class App {
    private App() {
        // Private constructor to prevent instantiation
    }

    public static void main(String[] args) {
        String filePath = App.class.getResource(String.format("/%s", args[0])).getPath();
        final RoutesBuilder routesBuilder = new RoutesBuilder(filePath);
        routesBuilder.execute();
    }
}
