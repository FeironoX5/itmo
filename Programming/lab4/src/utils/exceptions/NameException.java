package utils.exceptions;

/**
 * {@code NameException} расширяет {@link Exception} и
 * используется чтобы предупредить о неправильном значении
 * строки, отражающей имя (напр. названия компонента).
 * Это <em>проверяемое исключение</em>, а значит, его нужно обработать.
 *
 * @author Gleb Kiva
 */
public class NameException extends Exception {
    /**
     * Конструктор, инициализирующий {@link NameException}.
     *
     * @param message Сообщение исключения
     */
    public NameException(final String message) {
        super(message);
    }
}
