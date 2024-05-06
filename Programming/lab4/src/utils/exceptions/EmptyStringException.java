package utils.exceptions;

/**
 * {@code NonNullStringException} расширяет {@link Exception} и
 * используется чтобы предупредить о пустом значении строки.
 * Это <em>проверяемое исключение</em>, а значит, его нужно обработать.
 *
 * @author Gleb Kiva
 */
public class EmptyStringException extends IllegalArgumentException {
    /**
     * Конструктор, инициализирующий {@link NameException}.
     *
     * @param message Сообщение исключения
     */
    public EmptyStringException() {
        super("Строка не может принимать пустое значение");
    }

    public EmptyStringException(final String message) {
        super(message);
    }
}
