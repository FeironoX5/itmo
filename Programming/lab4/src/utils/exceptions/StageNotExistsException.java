package utils.exceptions;

/**
 * {@code StageNotExistsException} расширяет {@link RuntimeException} и
 * и используется чтобы сообщить о попытке обратиться к несуществующей
 * ступени ракеты.
 * Это <em>непроверяемое исключение</em>, а значит может возникнуть
 * в любой момент при выполнении программы.
 *
 * @author Gleb Kiva
 */
public class StageNotExistsException extends RuntimeException {
    /**
     * Конструктор, инициализирующий {@link StageNotExistsException}.
     */
    public StageNotExistsException() {
        super("Попытка отсоединения несуществующей ступени");
    }
}
