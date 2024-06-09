package teapot.utils;

import teapot.ServerRunner;
import teapot.models.Command;
import teapot.models.Response;
import teapot.utils.handlers.RequestHandler;
import teapot.utils.managers.CollectionManager;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

/*
 * осуществляет выполнение команд по управлению коллекцией
 * ракет по запросу клиента, а также управляет портами
 */
public class Server<T extends Comparable<T>> {
    private final CollectionManager<T> collectionManager;
    private final HashMap<Integer, Thread> portListeners;

    public Server(CollectionManager<T> collectionManager) {
        this.collectionManager = collectionManager;
        portListeners = new HashMap<>();
        Console console = new Console(new HashMap<>() {{
            put("save", createSaveCommand());
            put("close", createCloseCommand());
        }});
        ServerRunner.logger.info("Server initialized");
        // save collection if application stopped working
        Runtime.getRuntime().addShutdownHook(new Thread(collectionManager::save));
        // let's now run console in separate thread
        getConsoleThread(console).start();
    }

    private Thread getConsoleThread(Console console) {
        class ConsoleRunner implements Runnable {
            private final Console console;
            private final RequestHandler requestHandler;

            public ConsoleRunner(Console console, RequestHandler requestHandler) {
                this.console = console;
                this.requestHandler = requestHandler;
            }

            @Override
            public void run() {
                requestHandler.sayHello();
                console.run(requestHandler);
                // closing ports
                closePortListeners();
            }
        }
        ServerRequestHandler serverRequestHandler = new ServerRequestHandler();
        return new Thread(new ConsoleRunner(console, serverRequestHandler));
    }

    public void listenPort(int port, CommandManager commandManager) {
        String threadName = String.format("%s port listener", port);
        Runnable threadAction = new PortListener(port, commandManager);
        portListeners.put(port, new Thread(threadAction, threadName));
        portListeners.get(port).start();
    }

    /**
     * Saves the collection to a file.
     */
    private Command createSaveCommand() {
        return new Command(
                "Save the collection to a file.",
                (String... args) -> {
                    Response response = new Response();
                    collectionManager.save();
                    response.print("Changes saved");
                    return response;
                });
    }

    /**
     * Closes server port listening.
     */
    private Command createCloseCommand() {
        return new Command(
                "Closes connection.",
                new TreeMap<>() {{
                    put("port", Integer.class);
                }},
                (String... args) -> {
                    Response response = new Response();
                    int port = Integer.parseInt(args[0]);
                    try {
                        closePortListener(port);
                        response.print("Connection closed");
                    } catch (IllegalArgumentException e) {
                        response.err("No listener with such port");
                    }
                    return response;
                });
    }

    public void closePortListener(int port) throws IllegalArgumentException {
        try {
            portListeners.get(port).interrupt();
            portListeners.remove(port);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException(e);
        } finally {
            ServerRunner.logger.info(String.format("Port %s listener was closed", port));
        }
    }

    public void closePortListeners() {
        Set<Integer> activePorts = new HashSet<>(portListeners.keySet());
        activePorts.forEach(this::closePortListener);
    }
}
