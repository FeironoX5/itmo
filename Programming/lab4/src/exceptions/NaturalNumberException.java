package exceptions;

/**
 * The class {@code NaturalNumberException} is a form of {@link NumberException},
 * that indicates passing a negative, or zero value for an argument,
 * which represents natural one (e.g. length or weight).
 * This exception is a <em>checked exception,</em>
 * which means, it needs to be processed.
*/

public class NaturalNumberException extends NumberException {
    public NaturalNumberException(String message) {
        super(message);
    }
}
