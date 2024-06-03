package teapot.utils;

import teapot.models.Response;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * содержит методы для запуска нужной команды
 */
public class CommandManager {
    private final HashMap<String, Command> commandByAbbreviationMap;
    private final LinkedList<String> history;

    public CommandManager() {
        commandByAbbreviationMap = new HashMap<>();
        history = new LinkedList<>();
    }

    public Response runCommand(String abbreviation, String... args) {
        Command c = commandByAbbreviationMap.getOrDefault(
                abbreviation,
                new Command((String... emptyArgs) -> new Response().err("No such command")));
        try {
            Response response = c.execute(args);
            history.addLast(abbreviation);
            if (history.size() > 15) {
                history.removeFirst();
            }
            return response;
        } catch (IllegalArgumentException e) {
            return new Response().err(String.format("Illegal argument found: %s", e.getMessage()));
        }
    }

    public void addCommand(String abbreviation, Command command) {
        commandByAbbreviationMap.put(abbreviation, command);
    }

    public void addCommands(HashMap<String, Command> commands) {
        commandByAbbreviationMap.putAll(commands);
    }

    public HashMap<String, Command> getCommands() {
        return commandByAbbreviationMap;
    }

    public LinkedList<String> getHistory() {
        return history;
    }
}
