package exceptions;

/**
 * The class {@code NumberException} is a form of {@ Exception},
 * that indicates passing a wrong value for a number argument.
 * This exception is a <em>checked exception,</em>
 * which means, it needs to be processed.
*/

public class NumberException extends Exception {
    public NumberException(String message) {
        super(message);
    }
}
