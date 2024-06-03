package teapot.utils.interfaces;

import teapot.models.Response;

public interface Executable {
    Response execute(String... args);
}