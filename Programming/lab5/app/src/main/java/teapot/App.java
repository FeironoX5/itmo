package teapot;

import teapot.builder.RoutesBuilder;

public class App {
    public static void main(String[] args) {
        String filePath = App.class.getResource(String.format("/%s", args[0])).getPath();
        final RoutesBuilder routesBuilder = new RoutesBuilder(filePath);
        routesBuilder.execute();
    }
}
