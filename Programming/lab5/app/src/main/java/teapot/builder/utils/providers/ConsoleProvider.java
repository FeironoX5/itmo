package teapot.builder.utils.providers;

import java.util.ArrayList;
import java.util.Arrays;

import teapot.builder.utils.Console;

public class ConsoleProvider {
    public static ArrayList<String> runScript(String absolutePath, Console console) {
        ArrayList<String> res = new ArrayList<>();
        ArrayList<String> lines = FileProvider.load(absolutePath);
        lines.forEach(line -> {
            String[] userCommand = line.toLowerCase().split("\\s+");
            console.runCommand(userCommand[0], Arrays.copyOfRange(userCommand, 1, userCommand.length));
        });
        return res;
    }
}
