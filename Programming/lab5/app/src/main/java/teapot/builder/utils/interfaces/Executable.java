package teapot.builder.utils.interfaces;

/**
 * Interface for executing operations with arguments.
 *
 * @author Gleb Kiva
 */
public interface Executable {

    /**
     * Executes an operation with the specified arguments.
     *
     * @param args The arguments needed to execute the operation.
     */
    void execute(String... args);
}
