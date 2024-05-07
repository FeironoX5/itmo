package teapot_rocket.components.inner;

import teapot_rocket.utils.ComponentBase;
import teapot_rocket.utils.RequirementHandler;
import teapot_rocket.utils.Vector;
import teapot_rocket.utils.exceptions.NonPositiveNumberException;
import teapot_rocket.utils.interfaces.MotorMount;

/**
 * Represents an inner engine component used in rocket construction.
 *
 * <p>
 * This class extends {@link InnerComponent} and implements the
 * {@link MotorMount} interface. It represents a rocket engine
 * component placed <b>located in rocket's tail.</b>
 * </p>
 *
 * <p>
 * The inner engine can calculate its movement vector based on velocity and
 * direction. It provides methods to set and retrieve the velocity of the
 * engine.
 * </p>
 *
 * <p>
 * To create an inner engine, specify its base information and wall thickness.
 * The velocity of the engine can be adjusted using the
 * {@link #setVelocity(double)} method during the flight.
 * </p>
 *
 * @author Gleb Kiva
 * @see ManoeuveringEngine
 */
public final class InnerEngine extends InnerComponent implements MotorMount {
    /**
     * The direction vector of the inner engine.
     */
    private final Vector vector;

    /**
     * The wall thickness of the inner engine.
     */
    public final double wallThickness;

    /**
     * The velocity of the inner engine (in units per second).
     */
    private double velocity;

    /**
     * Constructs an inner engine with the specified base information and wall
     * thickness.
     *
     * @param componentBase The base information of the inner engine.
     * @param wallThickness The wall thickness of the inner engine.
     * @throws IllegalArgumentException If the wall thickness is not positive.
     */
    public InnerEngine(final ComponentBase componentBase,
            final double wallThickness)
            throws IllegalArgumentException {
        super(componentBase);
        this.wallThickness = RequirementHandler.requirePositive(wallThickness);
        // Default direction vector (pointing downwards)
        this.vector = new Vector(0, 0, -1);
        // Default velocity
        setVelocity(0);
    }

    /**
     * Calculates the movement vector of the inner engine based
     * on its velocity and direction.
     *
     * @return The movement vector of the inner engine.
     */
    @Override
    public Vector calculateMovement() {
        return Vector.multiply(this.vector, velocity);
    }

    /**
     * Sets the velocity of the inner engine.
     *
     * @param velocity The velocity to set (in units per second).
     * @throws NonPositiveNumberException If the velocity is not positive.
     */
    @Override
    public void setVelocity(final double velocity)
            throws NonPositiveNumberException {
        this.velocity = RequirementHandler.requirePositive(velocity);
    }

    /**
     * Retrieves the current velocity of the inner engine.
     *
     * @return The current velocity of the inner engine (in units per second).
     */
    @Override
    public double getVelocity() {
        return velocity;
    }

    /**
     * Retrieves the direction vector of the inner engine.
     *
     * @return The direction vector of the inner engine.
     */
    @Override
    public Vector getVector() {
        return vector;
    }
}
