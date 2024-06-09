package teapot.utils;

import teapot.models.Command;
import teapot.models.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;


/**
 * содержит методы для запуска нужной команды
 */
public class CommandManager {
    private static final int MAX_HISTORY_SIZE = 15;
    private final HashMap<String, Command> commandByAbbreviationMap;
    private final Logger logger;
    private final LinkedList<String> history;


    /**
     * @param id символ, использующийся,
     *           например ServerCommandRequestHandler
     *           для идентификации менеджеров для выбора
     *           правильной команды help
     */
    public CommandManager(char id) {
        logger = LogManager.getLogger(String.format("Command Manager (%s)", id));
        commandByAbbreviationMap = new HashMap<>();
        commandByAbbreviationMap.put(String.format("help%s", id), new Command(
                """
                        Print help on available commands.""",
                (String... args) -> {
                    Response response = new Response();
                    var entries = new ArrayList<>(getCommands().entrySet());
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
                            response.print(String.format("> (%s) %s", arg.getValue().getSimpleName(), arg.getKey()));
                        }
                    }
                    return response;
                }));
        history = new LinkedList<>();
    }

    public Response runCommand(String abbreviation, String... args) {
        Command c = commandByAbbreviationMap.getOrDefault(
                abbreviation,
                new Command((String... emptyArgs) -> new Response().err("No such command")));
        try {
            logger.info(String.format("Command %s executing", abbreviation));
            Response response = c.execute(args);
            history.addLast(abbreviation);
            logger.info(String.format("Command %s added to history, history size: %s", abbreviation, history.size()));
            if (history.size() > MAX_HISTORY_SIZE) {
                logger.info("Top item removed from history");
                history.removeFirst();
            }
            return response;
        } catch (IllegalArgumentException e) {
            return new Response().err(String.format("Illegal argument found: %s", e.getMessage()));
        }
    }

    public boolean hasCommand(String abbreviation) {
        return commandByAbbreviationMap.containsKey(abbreviation);
    }

    public void addCommand(String abbreviation, Command command) {
        logger.info(String.format("Adding %s command", abbreviation));
        commandByAbbreviationMap.put(abbreviation, command);
    }

    public void addCommands(HashMap<String, Command> commands) {
        logger.info(String.format("Adding %s commands", commands.size()));
        commandByAbbreviationMap.putAll(commands);
    }

    public HashMap<String, Command> getCommands() {
        return commandByAbbreviationMap;
    }

    public LinkedList<String> getHistory() {
        return history;
    }
}
