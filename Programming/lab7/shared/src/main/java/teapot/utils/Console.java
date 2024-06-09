package teapot.utils;

import teapot.models.Command;
import teapot.models.CommandRequest;
import teapot.models.Response;
import teapot.utils.enums.ResponseCode;
import teapot.utils.handlers.RequestHandler;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

// читает выводит response в консоль

/**
 * Парсит и передает данные из консоли {@link RequestHandler}
 * в виде {@link CommandRequest} и выводит пришедший {@link Response} в консоль
 */
public final class Console {
    private final Scanner in = new Scanner(System.in);
    private final CommandManager consoleCommandsManager;

    public Console() {
        consoleCommandsManager = new CommandManager('c');
        consoleCommandsManager.addCommand(
                "exit",
                new Command(
                        """
                                Interrupts console application execution.""",
                        (String... args) -> {
                            Response response = new Response();
                            close();
                            return response;
                        })
        );
    }

    public Console(HashMap<String, Command> commands) {
        this();
        consoleCommandsManager.addCommands(commands);
    }

    public void run(final RequestHandler requestHandler) {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.print("\nType any command: ");
            String s = in.nextLine();
            if (s.isBlank()) continue;
            String[] userCommand = s.toLowerCase().split("\\s+");
            CommandRequest request = new CommandRequest(
                    userCommand[0],
                    Arrays.copyOfRange(userCommand, 1, userCommand.length));
            if (consoleCommandsManager.hasCommand(request.abbreviation())) {
                Response response = consoleCommandsManager
                        .runCommand(request.abbreviation(), request.args());
                printResponse(response);
                continue;
            }
            try {
                Response response = requestHandler.process(request);
                printResponse(response);
            } catch (InterruptedException e) {
                close();
            }
        }
    }

    void printResponse(Response response) {
        if (response.getCode() == ResponseCode.SUCCESS) {
            response.getData().forEach(System.out::println);
        } else {
            System.out.printf("Response with an error\nResponse code: %s\n", response.getCode());
            if (!response.getData().isEmpty()) response.getData().forEach(System.out::println);
        }
    }

    public void close() {
        Thread.currentThread().interrupt();
    }
}
