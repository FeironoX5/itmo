package utils.exceptions;

/**
 * {@code NaturalNumberException} расширяет {@link NumberException} и
 * используется чтобы предупредить о негативном, или нулевом значении
 * числового аргумента, который отвечает за натуральное число
 * (напр. длину, или вес).
 * Это <em>проверяемое исключение</em>, а значит, его нужно обработать.
 *
 * @author Gleb Kiva
 */
public class NaturalNumberException extends NumberException {
    /**
     * Конструктор, инициализирующий {@link NaturalNumberException}.
     *
     * @param message Сообщение исключения
     */
    public NaturalNumberException(final String message) {
        super(message);
    }
}
