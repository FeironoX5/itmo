package utils.implementations;

import utils.enums.EventType;

/**
 * {@code Event} это класс, позволяющий
 * передавать информацию о событиях.
 * События характеризуются {@link EventType},
 * на который подписываются {@link EventListener}.
 *
 * @param <T> Тип данных информации о событии
 *
 * @author Gleb Kiva
 * @see EventBus
 * @see EventListener
 */

// FIXME TextPld EngineStartedPayload, AnonimPayload -> EngineStartedPaylaod
public class Event<T> {
    /**
     * Данные типа {@code T} о событии.
     */
    private final T data;
    /**
     * Тип, на который подписываются {@link EventListener}.
     */
    private final EventType eventType;

    /**
     * Создаёт событие с заданными данными и типом.
     *
     * @param data      Данные о событии
     * @param eventType Тип события, характеризуемых {@link EventType}
     */
    public Event(final T data, final EventType eventType) {
        this.data = data;
        this.eventType = eventType;
    }

    /**
     * Возвращает данные о событии.
     *
     * @return Данные о событии
     */
    public T getData() {
        return data;
    }

    /**
     * Возвращает тип события.
     *
     * @return Тип события
     */
    public EventType getType() {
        return eventType;
    }
}
