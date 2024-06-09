package teapot.models.rocket.components.body;

import java.util.LinkedList;

import teapot.models.rocket.Rocket;
import teapot.models.rocket.components.inner.InnerComponent;
import teapot.models.rocket.utils.ComponentBase;
import teapot.models.rocket.utils.Vector;
import teapot.models.rocket.utils.enums.EventType;
import teapot.models.rocket.utils.implementations.Event;
import teapot.models.rocket.utils.interfaces.EventListener;
import teapot.models.rocket.utils.interfaces.Rotatable;
import teapot.utils.handlers.RequirementHandler;

/**
 * Represents a body tube component used in rocket construction.
 *
 * <p>
 * This class extends {@link BodyComponent} and
 * implements the {@link Rotatable} interface.
 * It represents a cylindrical body tube of a rocket with wall thickness and
 * rotational capabilities.
 * </p>
 *
 * @author Gleb Kiva
 * @see Rotatable
 */
public final class BodyTube extends BodyComponent implements Rotatable {
    /**
     * The wall thickness of the body tube.
     */
    public final double wallThickness;

    /**
     * The current rotation angle of the body tube (in degrees).
     */
    private double rotation;

    /**
     * Controller for indoor temperature regulation within the body tube.
     */
    private final IndoorTemperatureController indoorTemperatureController;

    /**
     * Constructs a body tube with the specified base information,
     * inner components, and wall thickness.
     *
     * @param componentBase The base information of the body tube.
     * @param inners        The list of {@link InnerComponent} contained within the
     *                      body tube.
     * @param wallThickness The wall thickness of the body tube.
     * @throws IllegalArgumentException If the wall thickness is not positive.
     */
    public BodyTube(final ComponentBase componentBase,
            final LinkedList<InnerComponent> inners, final double wallThickness)
            throws IllegalArgumentException {
        super(componentBase, inners);
        this.wallThickness = RequirementHandler.requirePositive(wallThickness);
        this.rotation = 0;
        this.indoorTemperatureController = new IndoorTemperatureController();
    }

    /**
     * Inner class for controlling indoor temperature within the body tube.
     */
    class IndoorTemperatureController implements EventListener {
        /**
         * The current indoor temperature.
         */
        private float indoorTemperature;

        /**
         * Constructs the indoor temperature controller and subscribes to relevant
         * events.
         */
        IndoorTemperatureController() {
            Rocket.eventBus.addListener(EventType.STAGE_SEPARATED, this);
        }

        /**
         * Retrieves the current indoor temperature.
         *
         * @return The current indoor temperature.
         */
        public float getIndoorTemperature() {
            return indoorTemperature;
        }

        /**
         * Handles events related to indoor temperature control.
         *
         * @param event The event to handle.
         */
        @Override
        public void handle(final Event<?> event) {
            // TODO Event handling implementation
            switch (event.getType()) {
                case STAGE_SEPARATED:
                    // Handle stage separation event
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * Calculates the movement vector of the body tube.
     * </p>
     *
     * @return The movement vector which body tube provides.
     */
    @Override
    public Vector calculateMovement() {
        Vector vector = super.calculateMovement();
        return Vector.rotateZ(vector, rotation);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * Rotates the body tube by the specified angle (in degrees).
     * </p>
     *
     * @param delta The angle by which to rotate the body tube.
     */
    @Override
    public void rotate(final double delta) {
        rotation = (rotation + delta + 180) % 360 - 180;
        System.out.printf("Rotation of %s set to %.1f degrees\n", name, rotation);
    }

    /**
     * Retrieves the current rotation angle of the body tube.
     *
     * @return The current rotation angle (in degrees).
     */
    @Override
    public double getRotation() {
        return rotation;
    }
}
