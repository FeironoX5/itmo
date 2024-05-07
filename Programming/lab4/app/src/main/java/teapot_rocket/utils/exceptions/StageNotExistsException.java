package teapot_rocket.utils.exceptions;

import teapot_rocket.utils.RequirementHandler;

/**
 * Signals that an attempt was made to access a non-existent rocket stage.
 *
 * <p>
 * This exception extends {@code RuntimeException} and is used to indicate that
 * an attempt was made to access a non-existent stage of a rocket. It is an
 * unchecked exception, meaning it can occur at any point during program
 * execution.
 * </p>
 *
 * <p>
 * The default constructor provides a specific error message indicating the
 * attempt to detach a non-existent stage.
 * </p>
 *
 * @see RequirementHandler
 */
public class StageNotExistsException extends RuntimeException {

    /**
     * Constructs a {@code StageNotExistsException} with a default error message.
     */
    public StageNotExistsException() {
        super("Попытка отсоединения несуществующей ступени");
    }
}
