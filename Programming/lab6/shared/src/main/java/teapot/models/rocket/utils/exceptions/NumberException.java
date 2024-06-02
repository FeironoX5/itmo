package teapot.rocket.utils.exceptions;

/**
 * Signals that a numeric argument has an invalid value.
 *
 * <p>
 * This exception extends {@code IllegalArgumentException} and is used to
 * indicate that a numeric argument has an invalid value. It is a checked
 * exception, meaning it must be handled appropriately.
 * </p>
 *
 * <p>
 * The default constructor provides a generic error message indicating that a
 * numeric argument has an invalid value. An overloaded constructor allows
 * specifying a custom error message.
 * </p>
 *
 * @author Gleb Kiva
 */
public class NumberException extends IllegalArgumentException {

    /**
     * Constructs a {@code NumberException} with a default error message.
     */
    public NumberException() {
        super("Числовой аргумент принимает неверное значение");
    }

    /**
     * Constructs a {@code NumberException} with the specified error message.
     *
     * @param message The detail message explaining the reason for the exception.
     */
    public NumberException(final String message) {
        super(message);
    }
}
