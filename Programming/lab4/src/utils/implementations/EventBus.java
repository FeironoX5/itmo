package utils.implementations;

import java.util.HashMap;
import java.util.HashSet;

import utils.interfaces.EventListener;
import utils.enums.EventType;

/**
 * {@code EventBus} это класс, реализующий
 * одноименный паттерн проектирования,
 * использующий класс-шину для обмена
 * нескольких классов событиями.
 *
 * @author Gleb Kiva
 * @see Event
 * @see EventListener
 */

public class EventBus {
    /**
     * {@code listenersByEventType} это {@code HashMap},
     * который по {@link EventType} позволяет
     * получить {@code HashSet} из {@link EventListener}.
     */
    private HashMap<EventType, HashSet<EventListener>> listenersByEventType;

    /**
     * Создаёт пустой {@code EventBus},
     * инициализируя {@link listenersByEventType}.
     */
    public EventBus() {
        listenersByEventType = new HashMap<>();
    }

    /**
     * Добавляет класс в множество уведомляемых,
     * в случае если произошло событие с указанным типом.
     *
     * @param eventType     Тип прослушиваемого события
     * @param eventListener Класс, который обработает событие
     *                      указанного типа в случае его возникновения
     */
    public final void addListener(
            final EventType eventType,
            final EventListener eventListener) {
        listenersByEventType.putIfAbsent(eventType, new HashSet<>());
        listenersByEventType.get(eventType).add(eventListener);
    }

    /**
     * Уведомляет классы подписанные на
     * тип события о том, что оно произошло.
     *
     * @param event Произошедшее событие
     */
    public final void notifyListeners(final Event<?> event) {
        listenersByEventType.get(event.getType()).stream()
                .forEach(listener -> listener.handle(event));
    }
}
