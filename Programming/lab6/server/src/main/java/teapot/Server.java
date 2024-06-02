package teapot;

/*
 * Осуществляет выполнение команд по управлению коллекцией
 * ракет по запросу клиента
 */
public class Server {
    private final int port;
    private final RequestHandler requestHandler;

    private Server() {
        requestHandler = new RequestHandler();
    }
}
