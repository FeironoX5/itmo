package teapot;


import teapot.models.Response;
import teapot.models.rocket.Rocket;
import teapot.models.rocket.components.assembly.Stage;
import teapot.models.rocket.utils.ComponentBase;
import teapot.models.rocket.utils.enums.Material;
import teapot.utils.*;

import java.util.*;

/*
 * Позволяет запустить сервер, определяя набор команд
 * для управления коллекцией ракет
 */
public class ServerRunner {
    private final String FILE_PATH;
    private final CollectionManager<Rocket> rocketsManager;
    private final CommandManager commandManager;
    private final Server server;

    public ServerRunner(String collectionResourceName) {
        var resourceURL = App.class.getResource(String.format("/%s", collectionResourceName));
        this.FILE_PATH = Objects.requireNonNull(resourceURL).getPath();
        // создать менеджер ракет и загрузить данные из файла
        rocketsManager = new CollectionManager<>();
        // TODO rocketsManager.addAll(CSVProvider.load(FILE_PATH, rocketConverter.instance));
        // создать менеджер команд и добавить нужные
        commandManager = new CommandManager();
        commandManager.addCommands(createCommandsMap());
        // задать сервер
        server = Server.instance;
    }

    public void runServer(int port) {
        server.listen(port, commandManager);
    }

    private HashMap<String, Command> createCommandsMap() {
        HashMap<String, Command> commandsMap = new HashMap<>();
        commandsMap.put("info", createInfoCommand());
        commandsMap.put("help", createHelpCommand());
        commandsMap.put("show", createShowCommand());
        commandsMap.put("showd", createShowDescCommand());
        commandsMap.put("search", createSearchCommand());
        commandsMap.put("save", createSaveCommand());
        commandsMap.put("clear", createClearCommand());
        commandsMap.put("hist", createHistoryCommand());
        commandsMap.put("add", createAddCommand());
        commandsMap.put("addst", createAddStageCommand());
        commandsMap.put("rm", createRemoveCommand());
        return commandsMap;
    }

    /**
     * Creates a Command for the 'info' command.
     * Prints information about the collection to the standard output stream.
     */
    private Command createInfoCommand() {
        return new Command(
                "Print information about the collection (type, initialization date, number of elements, etc.) to the standard output stream.",
                (String... args) -> {
                    Response response = new Response();
                    var rockets = rocketsManager.get();
                    response.print(String.format("Collection class: %s%n", rockets.getClass()));
                    response.print(String.format("Collection created at %s%n", rocketsManager.getCreationDate()));
                    response.print(String.format("Collection contains %s elements%n", rockets.size()));
                    return response;
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
                    Response response = new Response();
                    var entries = new ArrayList<>(commandManager.getCommands().entrySet());
                    for (var entry : entries) {
                        var title = entry.getKey().toUpperCase();
                        var divLength = 33 - title.length();
                        var divSymbols = new char[divLength / 2];
                        Arrays.fill(divSymbols, '=');
                        var div = new String(divSymbols);
                        response.print(String.join("",
                                div,
                                divLength % 2 == 0 ? " " : "= ",
                                title,
                                " ",
                                div));
                        response.print(entry.getValue().description);
                        for (var arg : entry.getValue().requiredArgs.entrySet()) {
                            response.print(String.format("> (%s) %s%n", arg.getValue().getSimpleName(), arg.getKey()));
                        }
                    }
                    return response;
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
                    Response response = new Response();
                    var rockets = rocketsManager.get();
                    rockets.forEach(response::print);
                    return response;
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
                    Response response = new Response();
                    var rockets = rocketsManager.getSorted();
                    rockets.forEach(rocket -> response.print(rocket.name));
                    return response;
                });
    }

    /**
     * Creates a Command for the 'search' command.
     * Prints the elements whose name field value starts with the given substring.
     */
    private Command createSearchCommand() {
        return new Command(
                "Print the elements whose name field value starts with the given substring.",
                new TreeMap<>() {{
                    put("searched value", String.class);
                }},
                (String... args) -> {
                    Response response = new Response();
                    var query = args[0];
                    var rockets = rocketsManager.search(rocket -> rocket.name.startsWith(query));
                    rockets.forEach(response::print);
                    return response;
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
                    var data = rocketsManager.get();
                    return FileProvider.save(FILE_PATH, Serializer.toBytes(data));
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
                    Response response = new Response();
                    rocketsManager.clear();
                    return response;
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
                    Response response = new Response();
                    commandManager.getHistory().forEach(response::print);
                    return response;
                });
    }

    /**
     * Creates a Command for the 'add' command.
     * Adds a new item to the collection.
     */
    private Command createAddCommand() {
        return new Command(
                "Add a new item to the collection.",
                new TreeMap<>() {{
                    put("name", String.class);
                    put("manufacturer", String.class);
                    put("originCountry", String.class);
                }},
                (String... args) -> {
                    Response response = new Response();
                    rocketsManager.add(new Rocket(args[0], args[1], args[2]));
                    return response;
                });
    }

    /**
     * Creates a Command for the 'rm' command.
     * Removes an item from the collection by its id.
     */
    private Command createRemoveCommand() {
        return new Command(
                "Remove an item from the collection by its name.",
                new TreeMap<>() {{
                    put("rocket's name", String.class);
                }},
                (String... args) -> {
                    Response response = new Response();
                    rocketsManager.filter(rocket -> Objects.equals(rocket.name, args[0]));
                    return response;
                });
    }

    /**
     * Creates a Command for the 'upd' command.
     * Updates the value of the element in the collection whose id is equal to the
     * given one.
     */
    private Command createAddStageCommand() {
        return new Command(
                "Add new stage to rocket with the given name.",
                new TreeMap<>() {{
                    put("rocket name", String.class);
                    put("stage name", String.class);
                    put("width", Double.class);
                    put("height", Double.class);
                    put("weight", Double.class);
                    put("material", Material.class);
                }},
                (String... args) -> {
                    Response response = new Response();
                    var foundRocket = rocketsManager.searchFirst(
                            rocket -> Objects.equals(rocket.name, args[0]));
                    foundRocket.addStage(new Stage(
                            new ComponentBase(
                                    args[0],
                                    Double.parseDouble(args[1]),
                                    Double.parseDouble(args[2]),
                                    Double.parseDouble(args[3]),
                                    Material.valueOf(args[4])
                            ),
                            new LinkedList<>()
                    ));
                    return response;
                });
    }

}
