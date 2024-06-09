package teapot.utils.handlers;

import teapot.models.CommandRequest;
import teapot.models.Response;

public interface RequestHandler {
    void sayHello();

    Response process(CommandRequest request) throws InterruptedException;
}
