package teapot;

import java.util.HashMap;

import teapot.builder.utils.enums.CommandType;
import teapot.builder.utils.interfaces.Command;
import teapot.builder.utils.managers.ConsoleManager;

public class App {
    public static void main(String[] args) {
        var consoleManager = ConsoleManager.instance;
        consoleManager.addCommands(new HashMap<CommandType, Command>() {
            {
                put(CommandType.EXECUTE_SCRIPT, (String... args) -> {
                    String path = args[0];
                    // TODO read file
                    System.out.println("0 commands executed");
                });
                put(CommandType.INFO, (String... args) -> {
                    StringBuilder builder = new StringBuilder();
                    // for (var i : data) {
                    // builder.append(i);
                    // builder.append(" ");
                    // }
                    System.out.println(builder.toString());
                });
                put(CommandType.HELP, (String... args) -> {
                    consoleManager.getCommands().keySet().forEach(key -> {
                        System.out.println(String.format("===== %s =====", key.getAbbreviation()));
                        System.out.println(key.getDescription());
                    });
                });
            }
        });
        consoleManager.listen();
    }
}
