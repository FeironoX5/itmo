package teapot.builder.utils.interfaces;

@FunctionalInterface
public interface Command {
    void execute(String... args);
}
