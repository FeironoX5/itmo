package exceptions;

/**
 * The class {@code NameException} is a form of {@code Exception},
 * that indicates passing an invalid String as argument.
 * This exception is a <em>checked exception,</em>
 * which means, it needs to be processed.
 */

public class NameException extends Exception {
    public NameException(String message) {
        super(message);
    }
}
