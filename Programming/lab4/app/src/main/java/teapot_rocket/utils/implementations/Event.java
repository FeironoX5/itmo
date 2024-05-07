package teapot_rocket.utils.implementations;

import teapot_rocket.utils.enums.EventType;
import teapot_rocket.utils.implementations.Event;
import teapot_rocket.utils.interfaces.EventListener;

/**
 * The {@code Event} class represents an event that carries information of type
 * {@code T}. Events are characterized by {@link EventType} and can be
 * subscribed to by {@link EventListener}.
 *
 * @param <T> The type of data carried by the event.
 *
 * @author Gleb Kiva
 * @see EventBus
 * @see EventListener
 */
public class Event<T> {
    /**
     * The data associated with the event of type {@code T}.
     */
    private final T data;
    /**
     * The type of event, as defined by {@link EventType}.
     */
    private final EventType eventType;

    /**
     * Constructs an event with the specified data and event type.
     *
     * @param data      The data associated with the event.
     * @param eventType The type of the event, characterized by {@link EventType}.
     */
    public Event(final T data, final EventType eventType) {
        this.data = data;
        this.eventType = eventType;
    }

    /**
     * Retrieves the data associated with the event.
     *
     * @return The data associated with the event.
     */
    public T getData() {
        return data;
    }

    /**
     * Retrieves the type of the event.
     *
     * @return The type of the event.
     */
    public EventType getType() {
        return eventType;
    }
}
