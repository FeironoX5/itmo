package teapot.rocket.components.mass;

import teapot.rocket.Rocket;
import teapot.rocket.utils.ComponentBase;
import teapot.rocket.utils.RequirementHandler;
import teapot.rocket.utils.enums.EventType;
import teapot.rocket.utils.enums.FlightPhases;
import teapot.rocket.utils.implementations.Event;
import teapot.rocket.utils.interfaces.EventListener;

/**
 * Represents a parachute component used in rocket construction.
 *
 * <p>
 * This class extends {@link MassComponent} and represents a parachute designed
 * to assist in rocket recovery by enabling controlled descent.
 * </p>
 *
 * <p>
 * This class subscribes to the {@link EventType#FLIGHT_PHASE_ENTERED} event
 * from the {@link Rocket#eventBus}, enabling it to react to specific flight
 * phases during rocket operation.
 * </p>
 *
 * @author Gleb Kiva
 * @see FlightPhases
 * @see teapot.rocket.utils.implementations.EventBus
 */
public final class Parachute extends MassComponent implements EventListener {

    /**
     * The maximum weight capacity that the parachute can safely handle (in
     * kilograms).
     */
    public final double weightCapacity;

    /**
     * Constructs a parachute component with the specified base information and
     * weight capacity.
     *
     * @param componentBase  The base information of the parachute component.
     * @param weightCapacity The maximum weight capacity that the parachute can
     *                       handle (in kilograms).
     * @throws IllegalArgumentException If the {@code componentBase} is null or
     *                                  invalid,
     *                                  or if the {@code weightCapacity} is not
     *                                  positive.
     */
    public Parachute(final ComponentBase componentBase, final double weightCapacity)
            throws IllegalArgumentException {
        super(componentBase);
        this.weightCapacity = RequirementHandler.requirePositive(weightCapacity);
        Rocket.eventBus.addListener(EventType.FLIGHT_PHASE_ENTERED, this);
    }

    /**
     * Handles the specified event related to flight phases.
     *
     * <p>
     * This method is invoked when a flight phase event is received, enabling the
     * parachute based on the current phase of rocket flight.
     * </p>
     *
     * @param event The event object containing information about the flight phase.
     */
    @Override
    public void handle(final Event<?> event) {
        switch (event.getType()) {
            case FLIGHT_PHASE_ENTERED:
                if (event.getData() == FlightPhases.SLOW_DESCENT) {
                    // Deploy the parachute for safe descent during slow descent phase
                    System.out.println("Parachute deployed for slow descent phase");
                }
                break;
            default:
                break;
        }
    }

}
