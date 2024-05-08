package teapot_rocket.utils.implementations;

import java.util.HashMap;
import java.util.HashSet;

import teapot_rocket.utils.enums.EventType;
import teapot_rocket.utils.interfaces.EventListener;

/**
 * The {@code EventBus} class implements the EventBus pattern, which
 * uses a bus-like architecture to allow multiple classes to exchange
 * events.
 * <p>
 * Classes can subscribe to specific {@link EventType}s and receive
 * notifications when events of those types occur.
 *
 * @author Gleb Kiva
 * @see Event
 * @see EventListener
 */
public class EventBus {
    /**
     * {@code listenersByEventType} is a {@code HashMap} that maps
     * {@link EventType}s to {@code HashSet}s of {@link EventListener}s.
     */
    private HashMap<EventType, HashSet<EventListener>> listenersByEventType;

    /**
     * Constructs an empty {@code EventBus} by initializing
     * {@code listenersByEventType}.
     */
    public EventBus() {
        listenersByEventType = new HashMap<>();
    }

    /**
     * Adds a class to the set of listeners for a specific event type.
     * The listener will handle events of the specified type when they occur.
     *
     * @param eventType     The type of event to listen for.
     * @param eventListener The listener that will handle events of the specified
     *                      type.
     */
    public final void addListener(
            final EventType eventType,
            final EventListener eventListener) {
        listenersByEventType.putIfAbsent(eventType, new HashSet<>());
        listenersByEventType.get(eventType).add(eventListener);
    }

    /**
     * Notifies all listeners subscribed to the event type that the event has
     * occurred.
     *
     * @param event The event that has occurred.
     */
    public final void notifyListeners(final Event<?> event) {
        listenersByEventType.get(event.getType()).forEach(listener -> listener.handle(event));
    }
}
