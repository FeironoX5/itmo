package utils.exceptions;

/**
 * {@code NonPositiveNumberException} расширяет {@link NumberException} и
 * используется чтобы предупредить о негативном, или нулевом значении
 * числового аргумента (напр. длины, или веса).
 * Это <em>проверяемое исключение</em>, а значит, его нужно обработать.
 *
 * @author Gleb Kiva
 */
public class NonPositiveNumberException extends NumberException {
    /** //FIXME эти комментарии пофикси ради бога
     * Конструктор, инициализирующий {@link NaturalNumberException}.
     *
     * @param message Сообщение исключения
     */
    public NonPositiveNumberException() {
        super("Положительный числовой аргумент принимает неверное значение");
    }

    public NonPositiveNumberException(final String message) {
        super(message);
    }
}
