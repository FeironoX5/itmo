package teapot.builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import teapot.builder.models.Route;
import teapot.builder.utils.CSVProvider;
import teapot.builder.utils.CollectionManager;
import teapot.builder.utils.Command;
import teapot.builder.utils.Console;
import teapot.builder.utils.RequirementHandler;
import teapot.builder.utils.converters.RouteConverter;
import teapot.builder.utils.interfaces.Executable;

public class RoutesBuilder implements Executable {
    private String FILE_PATH;
    private final Console console;
    private final CollectionManager<Route> routesManager;

    public RoutesBuilder(String filePath) {
        FILE_PATH = RequirementHandler.requireNonEmptyString(filePath);
        console = Console.instance;
        routesManager = new CollectionManager<>();
        routesManager.addAll(CSVProvider.load(FILE_PATH, RouteConverter.instance));
        console.addCommands(new HashMap<String, Command>() {
            {
                put("exec", new Command(
                        """
                                Read and execute a script from the
                                specified file. The script contains
                                commands as the user enters them
                                interactively.""",
                        new Class<?>[] { String.class },
                        (String... args) -> {
                            // String path = args[0];
                            // TODO read file
                            System.out.println("0 commands executed");
                        }));
                put("info", new Command(
                        """
                                Print information about the collection
                                (type, initialization date, number of
                                elements, etc.) to the standard output
                                stream.""",
                        (String... args) -> {
                            var routes = routesManager.get();
                            System.out.println(
                                    String.format(
                                            "Collection class: %s", routes.getClass()));
                            System.out.println(
                                    String.format(
                                            "Collection created at %s", routesManager.getCreationDate()));
                            System.out.println(
                                    String.format(
                                            "Collection contains %s elements", routes.size()));
                        }));
                put("help", new Command(
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
                        }));
                put("show", new Command(
                        """
                                Output all elements of the collec-
                                tion in string representation
                                to the standard output stream.""",
                        (String... args) -> {
                            var routes = routesManager.get();
                            routes.forEach(route -> System.out.println(route));
                        }));
                put("showd", new Command(
                        """
                                Print the distance field values of
                                all items in descending order.""",
                        (String... args) -> {
                            var routes = routesManager.getSorted();
                            routes.forEach(route -> System.out.println(route.getBase().getDistance()));
                        }));
                put("search", new Command(
                        """
                                Print the elements whose name field
                                value starts with the given sub-
                                string.""",
                        new Class<?>[] { String.class },
                        (String... args) -> {
                            var query = args[0];
                            var routes = routesManager.search(route -> route.getBase().getName().startsWith(query));
                            routes.forEach(route -> System.out.println(route));
                        }));
                put("save", new Command(
                        """
                                Save the collection to a file.""",
                        (String... args) -> {
                            CSVProvider.save(FILE_PATH, routesManager.get(), RouteConverter.instance);
                        }));
                put("clear", new Command(
                        """
                                Clear the collection.""",
                        (String... args) -> {
                            routesManager.clear();
                        }));
                put("hist", new Command(
                        """
                                Print the last 15 commands (with-
                                out their arguments)""",
                        (String... args) -> {
                            console.getHistory().forEach(abbreviation -> System.out.println(abbreviation));
                        }));
                put("exit", new Command(
                        """
                                End the program (without saving
                                to a file).""",
                        (String... args) -> {
                            console.close();
                        }));
                put("add", new Command(
                        """
                                Add a new item to the collection.""",
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
                        }));
                put("rm", new Command(
                        """
                                Remove an item from the collection
                                by its id.""",
                        new Class<?>[] { Integer.class },
                        (String... args) -> {
                            var id = Integer.parseInt(args[0]);
                            routesManager.filter(route -> route.getId() == id);
                        }));
                put("upd", new Command(
                        """
                                Update the value of the element in
                                the collection whose id is equal
                                to the given one.""",
                        // FIXME: to Map
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
                        }));
                put("rmg", new Command(
                        """
                                Remove all elements from the collec-
                                tion that are greater than the given
                                one.""",
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
                        }));
                put("rml", new Command(
                        """
                                Remove from the collection all ele-
                                ments smaller than the given one.""",
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
                        }));
                put("rmd", new Command(
                        """
                                Remove from the collection all
                                items whose distance field value
                                is equivalent to the given one.""",
                        new Class<?>[] { Long.class },
                        (String... args) -> {
                            long distance = Long.parseLong(args[0]);
                            routesManager.filter(route -> route.getBase().getDistance() == distance);
                        }));
            }
        });
    }

    @Override
    public void execute(String... args) {
        console.listen();
    }
}
