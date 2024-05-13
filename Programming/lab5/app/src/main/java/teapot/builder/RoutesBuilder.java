package teapot.builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.stream.Stream;

import teapot.builder.models.ComparableByDistance;
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
        routesManager.add(CSVProvider.load(FILE_PATH, RouteConverter.instance));
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
                            routes.forEach(route -> System.out.println(route.getDistance()));
                        }));
                put("search", new Command(
                        """
                                Print the elements whose name field
                                value starts with the given sub-
                                string.""",
                        new Class<?>[] { String.class },
                        (String... args) -> {
                            var query = args[0];
                            var routes = routesManager.search(route -> route.getName().startsWith(query));
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
                            console.getHistory()
                                    .forEach(abbreviation -> System.out.println(abbreviation));
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
                            var newRoute = RouteConverter.instance.decode(args);
                            routesManager.addConditional(newRoute, route -> route.getId() == newRoute.getId());
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
                            // STAY CAREFUL SHITTY CODE IN 2 LINES
                            int id = Integer.parseInt(args[0]);
                            var oldRoute = routesManager.searchFirst(route -> route.getId() == id);
                            // DANGER ZONE BAD CODE
                            // there is a price to pay for bad data organization.
                            // this is still not much
                            // @author scarleteagle
                            String[] newArgs = new String[args.length + 1];
                            for (int i = 0; i < 3; ++i) {
                                newArgs[i] = args[i];
                            }
                            newArgs[4] = oldRoute.getCreationDate().toString();
                            for (int i = 4; i < args.length; ++i) {
                                newArgs[i + 1] = args[i];
                            }
                            var newRoute = RouteConverter.instance.decode(newArgs); // DON'T TOUCH
                            // BE AWARE XXX 18+
                            // for free? @author scarleteagle
                            // var newRoute = RouteConverter.instance.decode(
                            // ArrayUtils.addAll(Arrays.copyOfRange(args, 1, args.length), second));
                            routesManager.filter(route -> route.getId() == id);
                            oldRoute.setData(newRoute);
                            routesManager.addConditional(oldRoute, route -> route.getId() == oldRoute.getId());
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
                            // var comparedRoute = new ComparableByDistance() {
                            // @Override
                            // public Long getDistance() {
                            // return Long.parseLong(args[9]);
                            // }
                            // };
                            // var comparedRoute = RouteConverter.instance.decode(args);
                            routesManager.filter(route -> route.compareTo(
                                    new ComparableByDistance() {
                                        @Override
                                        public Long getDistance() {
                                            return Long.parseLong(args[9]);
                                        }
                                    }) >= 0);
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
                            var comparedRoute = RouteConverter.instance.decodeTemp(args);
                            routesManager.filter(route -> route.compareTo(comparedRoute) <= 0);
                        }));
                put("rmd", new Command(
                        """
                                Remove from the collection all
                                items whose distance field value
                                is equivalent to the given one.""",
                        new Class<?>[] { Long.class },
                        (String... args) -> {
                            long distance = Long.parseLong(args[0]);
                            routesManager.filter(route -> route.getDistance() == distance);
                        }));
            }
        });
    }

    @Override
    public void execute(String... args) {
        console.listen();
    }
}
