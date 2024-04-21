package exceptions;

public class StageNotExistsException extends RuntimeException {
    public StageNotExistsException(String message) {
        super(message);
    }
}
