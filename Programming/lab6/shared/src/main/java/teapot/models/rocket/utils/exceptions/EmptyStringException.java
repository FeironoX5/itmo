package teapot.rocket.utils.exceptions;

/**
 * Signals that a string value is empty, which is not allowed.
 *
 * <p>
 * This exception extends {@code IllegalArgumentException} and is used to
 * indicate that an operation requiring a non-empty string was attempted with an
 * empty string.
 * </p>
 *
 * <p>
 * The default constructor provides a generic error message indicating that a
 * string cannot be empty. An overloaded constructor allows specifying a custom
 * error message.
 * </p>
 *
 * @author Gleb Kiva
 */
public class EmptyStringException extends IllegalArgumentException {

    /**
     * Constructs an {@code EmptyStringException} with a default error message.
     */
    public EmptyStringException() {
        super("Строка не может принимать пустое значение");
    }

    /**
     * Constructs an {@code EmptyStringException} with the specified error message.
     *
     * @param message The detail message explaining the reason for the exception.
     */
    public EmptyStringException(final String message) {
        super(message);
    }
}
