package teapot.builder.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * A simple console interface for interacting with commands.
 *
 * @author Gleb Kiva
 */
public final class Console {
    /**
     * Singleton instance of the Console.
     */
    public static final Console instance = new Console();

    private final Scanner in = new Scanner(System.in);
    private final HashMap<String, Command> commandByAbbreviationMap;
    private final LinkedList<String> history;
    private boolean running;

    /**
     * Constructs a new Console instance.
     */
    private Console() {
        commandByAbbreviationMap = new HashMap<>();
        history = new LinkedList<>();
        running = true;
    }

    /**
     * Starts listening for user input and executing commands until the console is
     * closed.
     */
    public void listen() {
        while (running) {
            System.out.print("\nType any command: ");
            String[] userCommand = in.nextLine().toLowerCase().split("\\s+");
            runCommand(userCommand[0], Arrays.copyOfRange(userCommand, 1, userCommand.length));
        }
    }

    /**
     * Executes a specified command with the given arguments.
     *
     * @param abbreviation The abbreviation or name of the command.
     * @param args         The arguments to be passed to the command.
     */
    public void runCommand(String abbreviation, String... args) {
        Command c = commandByAbbreviationMap.getOrDefault(abbreviation,
                new Command((String... emptyArgs) -> System.err.println("No such command")));
        try {
            c.execute(args);
            history.addLast(abbreviation);
            if (history.size() > 15) {
                history.removeFirst();
            }
        } catch (IllegalArgumentException e) {
            System.err.println(String.format("Illegal argument found: %s", e.getMessage()));
        }
    }

    /**
     * Adds a new command to the console with the specified abbreviation.
     *
     * @param abbreviation The abbreviation or name of the command.
     * @param command      The Command instance to be associated with the
     *                     abbreviation.
     */
    public void addCommand(String abbreviation, Command command) {
        commandByAbbreviationMap.put(abbreviation, command);
    }

    /**
     * Adds multiple commands to the console.
     *
     * @param commands A map of command abbreviations to Command instances.
     */
    public void addCommands(HashMap<String, Command> commands) {
        commandByAbbreviationMap.putAll(commands);
    }

    /**
     * Retrieves the map of command abbreviations to Command instances.
     *
     * @return The map of commands.
     */
    public HashMap<String, Command> getCommands() {
        return commandByAbbreviationMap;
    }

    /**
     * Retrieves the history of executed commands.
     *
     * @return The list of executed command abbreviations.
     */
    public LinkedList<String> getHistory() {
        return history;
    }

    /**
     * Closes the console, stopping further command execution.
     */
    public void close() {
        running = false;
    }
}
