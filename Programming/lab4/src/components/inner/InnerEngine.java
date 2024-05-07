package components.inner;

import components.Vector;
import utils.ComponentBase;
import utils.RequirementHandler;
import utils.exceptions.NonPositiveNumberException;
import utils.interfaces.MotorMount;

/**
 * Represents an inner engine component used in rocket construction.
 *
 * <p>
 * This class extends {@link InnerComponent} and implements the
 * {@link MotorMount} interface.
 * It represents a rocket engine component placed inside the rocket structure.
 * </p>
 *
 * <p>
 * The inner engine can calculate its movement vector based on velocity and
 * direction.
 * It provides methods to set and retrieve the velocity of the engine.
 * </p>
 *
 * <p>
 * To create an inner engine, specify its base information and wall thickness.
 * The velocity of the engine can be adjusted using the
 * {@link #setVelocity(double)} method.
 * </p>
 *
 * <p>
 * This class is designed to be used as an engine component within rocket
 * assemblies.
 * It implements the {@link MotorMount} interface for compatibility with rocket
 * motor systems.
 * </p>
 *
 * <p>
 * Inner engines are critical components that contribute to rocket propulsion
 * and movement.
 * </p>
 *
 * @author Gleb Kiva
 * @see InnerComponent
 * @see ManoeuveringEngine
 * @see MotorMount
 */
public class InnerEngine extends InnerComponent implements MotorMount {
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
