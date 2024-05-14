package teapot.builder.utils.providers;

import java.util.ArrayList;
import java.util.Arrays;

import teapot.builder.utils.Console;

/**
 * Utility class for running scripts from a file using a console.
 *
 * @author Gleb Kiva
 */
public final class ConsoleProvider {
    private ConsoleProvider() {
        // Private constructor to prevent instantiation
    }

    /**
     * Runs a script from a file using a specified console.
     *
     * @param absolutePath The absolute path of the script file to run.
     * @param console      The console used to execute commands from the script.
     */
    public static void runScript(String absolutePath, Console console) {
        ArrayList<String> lines = FileProvider.load(absolutePath);
        lines.forEach(line -> {
            String[] userCommand = line.toLowerCase().split("\\s+");
            console.runCommand(userCommand[0], Arrays.copyOfRange(userCommand, 1, userCommand.length));
        });
    }
}
