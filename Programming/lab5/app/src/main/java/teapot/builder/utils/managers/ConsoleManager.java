package teapot.builder.utils.managers;

import java.util.HashMap;
import java.util.Scanner;

import teapot.builder.utils.enums.CommandType;
import teapot.builder.utils.interfaces.Command;

public final class ConsoleManager {
    public static final ConsoleManager instance = new ConsoleManager();
    private final HashMap<CommandType, Command> commands;
    private final Scanner in;

    private ConsoleManager() {
        commands = new HashMap<>();
        in = new Scanner(System.in);
    }

    public void addCommand(CommandType commandType, Command command) {
        commands.put(commandType, command);
    }

    public void addCommands(HashMap<CommandType, Command> commands) {
        this.commands.putAll(commands);
    }

    public void listen() {
        while (true) {
            String s = in.nextLine();
            CommandType commandType = CommandType.getType(s);
            if (commandType == null) {
                System.out.println("No such command");
            } else {

                commands.get(commandType).execute();
            }
        }
    }

    public HashMap<CommandType, Command> getCommands() {
        return commands;
    }
}
