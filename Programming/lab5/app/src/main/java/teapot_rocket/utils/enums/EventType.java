package teapot_rocket.utils.enums;

/**
 * Represents different types of events that can occur in rocket systems.
 * Each event type corresponds to a specific event that can be handled by
 * the {@link teapot_rocket.utils.implementations.EventBus} and processed by
 * registered {@link teapot_rocket.utils.interfaces.EventListener}.
 * 
 * @author Gleb Kiva
 * @see teapot_rocket.utils.implementations.EventBus
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
