package utils.exceptions;

/**
 * {@code NumberException} расширяет {@link Exception} и
 * используется чтобы предупредить о неправильном значении
 * числового аргумента.
 * Это <em>проверяемое исключение</em>, а значит, его нужно обработать.
 *
 * @author Gleb Kiva
 */
public class NumberException extends IllegalArgumentException {
    /**
     * Конструктор, инициализирующий {@link NumberException}.
     *
     * @param message Сообщение исключения
     */
    public NumberException() {
        super("Числовой аргумент принимает неверное значение");
    }

    public NumberException(final String message) {
        super(message);
    }
}
