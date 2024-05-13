package teapot.builder.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public final class Console {
    public static final Console instance = new Console();
    private final Scanner in;
    private final HashMap<String, Command> commandByAbbreviationMap;
    private final LinkedList<String> history; // TODO change to queue
    private boolean running;

    private Console() {
        in = new Scanner(System.in);
        commandByAbbreviationMap = new HashMap<>();
        history = new LinkedList<>();
        running = true;
    }

    // TODO stdin/file stdout/file switches
    public void listen() {
        while (running) {
            System.out.print("\nType any command: ");
            String[] userCommand = in.nextLine().toLowerCase().split("\\s+");
            Command c = commandByAbbreviationMap.getOrDefault(userCommand[0],
                    new Command((String... args) -> System.err.println("No such command")));
            try {
                c.execute(Arrays.copyOfRange(userCommand, 1, userCommand.length));
                history.addLast(userCommand[0]);
                if (history.size() > 15) {
                    history.removeFirst();
                }
            } catch (IllegalArgumentException e) {
                System.err.println(String.format("Illegal argument found: %s", e.getMessage()));
            }
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

    public void close() {
        running = false;
    }
}
