package teapot.builder.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

import teapot.builder.utils.interfaces.Command;

public final class Console {
    public static final Console instance = new Console();
    private final Scanner in;
    private final HashMap<String, Command> commandByAbbreviationMap;
    private final ArrayList<String> history;

    private Console() {
        in = new Scanner(System.in);
        commandByAbbreviationMap = new HashMap<>();
        history = new ArrayList<>();
    }

    // TODO stdin/file stdout/file switches
    public void listen() {
        while (true) {
            String[] userCommand = in.nextLine().split("\\s+");
            Command c = commandByAbbreviationMap.getOrDefault(userCommand[0],
                    (String... args) -> System.err.println("No such command"));
            c.execute(Arrays.copyOfRange(userCommand, 1, userCommand.length));
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

    public Command[] getHistory() {
        ArrayList<Command> commands = new ArrayList<>();
        history.forEach(s -> {
            Command c = commandByAbbreviationMap.get(s);
            commands.add(c);
        });
        return commands.toArray(Command[]::new);
    }
}
