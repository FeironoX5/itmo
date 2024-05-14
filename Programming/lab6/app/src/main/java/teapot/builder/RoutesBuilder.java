package teapot.builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import teapot.App;
import teapot.builder.models.Route;
import teapot.builder.utils.CollectionManager;
import teapot.builder.utils.Command;
import teapot.builder.utils.Console;
import teapot.builder.utils.RequirementHandler;
import teapot.builder.utils.converters.RouteConverter;
import teapot.builder.utils.interfaces.Executable;
import teapot.builder.utils.providers.CSVProvider;
import teapot.builder.utils.providers.ConsoleProvider;

/**
 * RoutesBuilder - A class responsible for managing a collection of routes
 * through a command-line interface.
 * This class initializes a console for user interaction and provides various
 * commands to manipulate the route collection.
 *
 * @author Gleb Kiva
 */
public class RoutesBuilder implements Executable {

    private String FILE_PATH;
    private final Console console;
    private final CollectionManager<Route> routesManager;

    /**
     * Constructs a new RoutesBuilder instance with the specified file path.
     * 
     * @param filePath The path to the file from which the route collection will be
     *                 loaded.
     */
    public RoutesBuilder(String filePath) {
        FILE_PATH = RequirementHandler.requireNonEmptyString(filePath);
        console = Console.instance;
        routesManager = new CollectionManager<>();
        routesManager.addAll(CSVProvider.load(FILE_PATH, RouteConverter.instance));
        console.addCommands(createCommandsMap());
    }

    /**
     * Creates a map of commands for the console, associating command names with
     * their respective Command objects.
     * 
     * @return A HashMap containing command names as keys and corresponding Command
     *         objects as values.
     */
    private HashMap<String, Command> createCommandsMap() {
        HashMap<String, Command> commandsMap = new HashMap<>();
        commandsMap.put("exec", createExecCommand());
        commandsMap.put("info", createInfoCommand());
        commandsMap.put("help", createHelpCommand());
        commandsMap.put("show", createShowCommand());
        commandsMap.put("showd", createShowDescCommand());
        commandsMap.put("search", createSearchCommand());
        commandsMap.put("save", createSaveCommand());
        commandsMap.put("clear", createClearCommand());
        commandsMap.put("hist", createHistoryCommand());
        commandsMap.put("exit", createExitCommand());
        commandsMap.put("add", createAddCommand());
        commandsMap.put("rm", createRemoveCommand());
        commandsMap.put("upd", createUpdateCommand());
        commandsMap.put("rmg", createRemoveGreaterCommand());
        commandsMap.put("rml", createRemoveLessCommand());
        commandsMap.put("rmd", createRemoveByDistanceCommand());
        return commandsMap;
    }

    /**
     * Creates a Command for the 'exec' command.
     * Reads and executes a script from the specified file.
     */
    private Command createExecCommand() {
        return new Command(
                "Read and execute a script from the specified file. The script contains commands as the user enters them interactively.",
                new Class<?>[] { String.class },
                (String... args) -> {
                    ConsoleProvider.runScript(
                            App.class.getResource(String.format("/%s", args[0])).getPath(),
                            console);
                });
    }

    /**
     * Creates a Command for the 'info' command.
     * Prints information about the collection to the standard output stream.
     */
    private Command createInfoCommand() {
        return new Command(
                "Print information about the collection (type, initialization date, number of elements, etc.) to the standard output stream.",
                (String... args) -> {
                    var routes = routesManager.get();
                    System.out.println(
                            String.format("Collection class: %s", routes.getClass()));
                    System.out.println(
                            String.format("Collection created at %s", routesManager.getCreationDate()));
                    System.out.println(
                            String.format("Collection contains %s elements", routes.size()));
                });
    }

    /**
     * Creates a Command for the 'help' command.
     * Prints help on available commands.
     */
    private Command createHelpCommand() {
        return new Command(
                """
                        Print help on available commands.""",
                (String... args) -> {
                    var entries = new ArrayList<>(console.getCommands().entrySet());
                    Collections.sort(entries, (a, b) -> a.getKey().compareTo(b.getKey()));
                    for (var entry : entries) {
                        var title = entry.getKey().toUpperCase();
                        var divLength = 33 - title.length();
                        var divSymbols = new char[divLength / 2];
                        Arrays.fill(divSymbols, '=');
                        var div = new String(divSymbols);
                        System.out.println(String.join("",
                                div,
                                divLength % 2 == 0 ? " " : "= ",
                                title,
                                " ",
                                div));
                        System.out.println(entry.getValue().description);
                        for (var arg : entry.getValue().requiredArgs) {
                            System.out.println(String.format("> %s", arg.getSimpleName()));
                        }
                        System.out.println();
                    }
                });
    }

    /**
     * Creates a Command for the 'show' command.
     * Outputs all elements of the collection in string representation to the
     * standard output stream.
     */
    private Command createShowCommand() {
        return new Command(
                "Output all elements of the collection in string representation to the standard output stream.",
                (String... args) -> {
                    var routes = routesManager.get();
                    routes.forEach(route -> System.out.println(route));
                });
    }

    /**
     * Creates a Command for the 'showd' command.
     * Prints the distance field values of all items in descending order.
     */
    private Command createShowDescCommand() {
        return new Command(
                "Print the distance field values of all items in descending order.",
                (String... args) -> {
                    var routes = routesManager.getSorted();
                    routes.forEach(route -> System.out.println(route.getBase().getDistance()));
                });
    }

    /**
     * Creates a Command for the 'search' command.
     * Prints the elements whose name field value starts with the given substring.
     */
    private Command createSearchCommand() {
        return new Command(
                "Print the elements whose name field value starts with the given substring.",
                new Class<?>[] { String.class },
                (String... args) -> {
                    var query = args[0];
                    var routes = routesManager.search(route -> route.getBase().getName().startsWith(query));
                    routes.forEach(route -> System.out.println(route));
                });
    }

    /**
     * Creates a Command for the 'save' command.
     * Saves the collection to a file.
     */
    private Command createSaveCommand() {
        return new Command(
                "Save the collection to a file.",
                (String... args) -> {
                    CSVProvider.save(FILE_PATH, routesManager.get(), RouteConverter.instance);
                });
    }

    /**
     * Creates a Command for the 'clear' command.
     * Clears the collection.
     */
    private Command createClearCommand() {
        return new Command(
                "Clear the collection.",
                (String... args) -> {
                    routesManager.clear();
                });
    }

    /**
     * Creates a Command for the 'hist' command.
     * Prints the last 15 commands (without their arguments).
     */
    private Command createHistoryCommand() {
        return new Command(
                "Print the last 15 commands (without their arguments).",
                (String... args) -> {
                    console.getHistory().forEach(abbreviation -> System.out.println(abbreviation));
                });
    }

    /**
     * Creates a Command for the 'exit' command.
     * Ends the program (without saving to a file).
     */
    private Command createExitCommand() {
        return new Command(
                "End the program (without saving to a file).",
                (String... args) -> {
                    console.close();
                });
    }

    /**
     * Creates a Command for the 'add' command.
     * Adds a new item to the collection.
     */
    private Command createAddCommand() {
        return new Command(
                "Add a new item to the collection.",
                new Class<?>[] {
                        String.class,
                        Integer.class,
                        Double.class,
                        Long.class, Float.class, String.class,
                        Long.class, Float.class, String.class,
                        Long.class },
                (String... args) -> {
                    var newRouteBase = RouteConverter.instance.routeBaseConverter.decode(args);
                    routesManager.add(new Route(newRouteBase));
                });
    }

    /**
     * Creates a Command for the 'rm' command.
     * Removes an item from the collection by its id.
     */
    private Command createRemoveCommand() {
        return new Command(
                "Remove an item from the collection by its id.",
                new Class<?>[] { Integer.class },
                (String... args) -> {
                    var id = Integer.parseInt(args[0]);
                    routesManager.filter(route -> route.getId() == id);
                });
    }

    /**
     * Creates a Command for the 'upd' command.
     * Updates the value of the element in the collection whose id is equal to the
     * given one.
     */
    private Command createUpdateCommand() {
        return new Command(
                "Update the value of the element in the collection whose id is equal to the given one.",
                new Class<?>[] { Integer.class,
                        String.class,
                        Integer.class,
                        Double.class,
                        Long.class, Float.class, String.class,
                        Long.class, Float.class, String.class,
                        Long.class },
                (String... args) -> {
                    int id = Integer.parseInt(args[0]);
                    var oldRoute = routesManager.searchFirst(route -> route.getId() == id);
                    var newRouteBase = RouteConverter.instance.routeBaseConverter.decode(
                            Arrays.copyOfRange(args, 1, args.length));
                    oldRoute.setBase(newRouteBase);
                    routesManager.filter(route -> route.getId() == id);
                    routesManager.add(oldRoute);
                });
    }

    /**
     * Creates a Command for the 'rmg' command.
     * Removes all elements from the collection that are greater than the given one.
     */
    private Command createRemoveGreaterCommand() {
        return new Command(
                "Remove all elements from the collection that are greater than the given one.",
                new Class<?>[] {
                        String.class,
                        Integer.class,
                        Double.class,
                        Long.class, Float.class, String.class,
                        Long.class, Float.class, String.class,
                        Long.class },
                (String... args) -> {
                    var comparedBase = RouteConverter.instance.routeBaseConverter.decode(args);
                    routesManager.filter(route -> route.getBase().compareTo(comparedBase) <= 0);
                });
    }

    /**
     * Creates a Command for the 'rml' command.
     * Removes from the collection all elements smaller than the given one.
     */
    private Command createRemoveLessCommand() {
        return new Command(
                "Remove from the collection all elements smaller than the given one.",
                new Class<?>[] {
                        String.class,
                        Integer.class,
                        Double.class,
                        Long.class, Float.class, String.class,
                        Long.class, Float.class, String.class,
                        Long.class },
                (String... args) -> {
                    var comparedBase = RouteConverter.instance.routeBaseConverter.decode(args);
                    routesManager.filter(route -> route.getBase().compareTo(comparedBase) >= 0);
                });
    }

    /**
     * Creates a Command for the 'rmd' command.
     * Removes from the collection all items whose distance field value is
     * equivalent to the given one.
     */
    private Command createRemoveByDistanceCommand() {
        return new Command(
                "Remove from the collection all items whose distance field value is equivalent to the given one.",
                new Class<?>[] { Long.class },
                (String... args) -> {
                    long distance = Long.parseLong(args[0]);
                    routesManager.filter(route -> route.getBase().getDistance() == distance);
                });
    }

    /**
     * Executes the RoutesBuilder application.
     * Displays the application's header and starts listening for user commands.
     * 
     * @param args Command-line arguments (not used in this context).
     */
    @Override
    public void execute(String... args) {
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
        console.listen();
    }
}
