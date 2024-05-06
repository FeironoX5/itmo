package utils.exceptions;

public class EmptyArrayException extends IllegalArgumentException {
    public EmptyArrayException() {
        super("Массив компонент не может быть пустым");
    }

    public EmptyArrayException(final String message) {
        super(message);
    }
}
