package teapot.rocket.utils.interfaces;

import teapot.rocket.utils.implementations.Event;

/**
 * The {@code EventListener} interface represents an object
 * that can handle events of specific types.
 * <p>
 * Implementing classes define a {@code handle} method
 * that is called when an event of interest occurs.
 * </p>
 *
 * @author Gleb Kiva
 * @see Event
 */
public interface EventListener {
    /**
     * Handles the specified event.
     *
     * @param event The event to handle.
     */
    void handle(Event<?> event);
}

// FIXME мб реализовать все расш. как анонимные классы?