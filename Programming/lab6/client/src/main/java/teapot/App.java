package teapot;

import teapot.builder.RoutesBuilder;

/**
 * Main application class for the Teapot application.
 *
 * <p>
 * Usage:
 * </p>
 * <ul>
 * <li>Using Java {@code java teapot.App <csv_file_name>}</li>
 * <li>Using Gradle
 * {@code gradle run --args='<csv_file_name>' --console=plain}</li>
 * </ul>
 *
 * <p>
 * Example:
 * </p>
 * <ul>
 * <li>Using Java {@code java teapot.App data.csv}</li>
 * <li>Using Gradle {@code gradle run --args='data.csv' --console=plain}</li>
 * </ul>
 *
 * @author Gleb Kiva
 */
public class App {
    private App() {
        // Private constructor to prevent instantiation
    }

    /**
     * The main entry point for the application.
     *
     * @param args Resource filename
     */
    public static void main(String[] args) {
        String filePath = App.class.getResource(String.format("/%s", args[0])).getPath();
        final RoutesBuilder routesBuilder = new RoutesBuilder(filePath);
        routesBuilder.execute();
    }
}
