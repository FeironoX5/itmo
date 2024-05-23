package teapot.builder.utils.exceptions;

/**
 * Signals that a numeric argument must be positive, but a non-positive value
 * was encountered.
 *
 * <p>
 * This exception extends {@code NumberException} and is used to indicate that a
 * numeric argument, such as length or weight, must be positive but was provided
 * with a non-positive value.
 * </p>
 *
 * <p>
 * The default constructor provides a generic error message indicating that a
 * positive numeric argument is expected. An overloaded constructor allows
 * specifying a custom error message.
 * </p>
 *
 * @author Gleb Kiva
 */
public class NonPositiveNumberException extends NumberException {

    /**
     * Constructs a {@code NonPositiveNumberException} with a default error message.
     */
    public NonPositiveNumberException() {
        super("Положительный числовой аргумент принимает неверное значение");
    }

    /**
     * Constructs a {@code NonPositiveNumberException} with the specified error
     * message.
     *
     * @param message The detail message explaining the reason for the exception.
     */
    public NonPositiveNumberException(final String message) {
        super(message);
    }
}
