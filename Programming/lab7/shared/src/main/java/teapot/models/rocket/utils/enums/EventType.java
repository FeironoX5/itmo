package teapot.models.rocket.utils.enums;

import teapot.models.rocket.utils.implementations.EventBus;
import teapot.models.rocket.utils.interfaces.EventListener;

/**
 * Represents different types of events that can occur in rocket systems.
 * Each event type corresponds to a specific event that can be handled by
 * the {@link EventBus} and processed by
 * registered {@link EventListener}.
 * 
 * @author Gleb Kiva
 * @see EventBus
 */
public enum EventType {

    /**
     * Event type representing the separation of a rocket stage.
     */
    STAGE_SEPARATED,

    /**
     * Event type representing the entry into a specific flight phase.
     *
     * @see FlightPhases
     */
    FLIGHT_PHASE_ENTERED,

    /**
     * Event type representing the setting of new power to a rocket engine.
     */
    ENGINE_POWER_SET;

    // TODO: Implement handling for ENGINE_POWER_SET event
    // TODO: Consider defining a Payload interface for event data

}
