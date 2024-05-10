package teapot.rocket.utils.exceptions;

/**
 * Exception thrown to indicate that an array of components is empty, which is not allowed.
 *
 * <p>
 * This exception extends {@code IllegalArgumentException} and is used to signal that an operation
 * requiring a non-empty array of components was attempted with an empty array.
 * </p>
 *
 * <p>
 * The default constructor provides a generic error message indicating that an array of components
 * cannot be empty. An overloaded constructor allows specifying a custom error message.
 * </p>
 *
 * @author Gleb Kiva
 */
public class EmptyArrayException extends IllegalArgumentException {

    /**
     * Constructs an {@code EmptyArrayException} with a default error message.
     */
    public EmptyArrayException() {
        super("Массив компонент не может быть пустым");
    }

    /**
     * Constructs an {@code EmptyArrayException} with the specified error message.
     *
     * @param message The detail message explaining the reason for the exception.
     */
    public EmptyArrayException(final String message) {
        super(message);
    }
}
