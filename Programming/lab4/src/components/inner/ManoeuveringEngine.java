package components.inner;

import components.Vector;
import utils.ComponentBase;
import utils.RequirementHandler;
import utils.exceptions.NonPositiveNumberException;
import utils.interfaces.MotorMount;

/**
 * Represents a manoeuvering engine component used in rocket construction.
 *
 * <p>
 * This class extends {@link InnerComponent} and implements the
 * {@link MotorMount} interface.
 * It represents a manoeuvering engine component placed inside the rocket
 * structure.
 * </p>
 *
 * <p>
 * The manoeuvering engine can calculate its movement vector based on velocity
 * and direction.
 * It provides methods to set and retrieve the velocity of the engine.
 * </p>
 *
 * <p>
 * To create a manoeuvering engine, specify its base information and rotation
 * angle.
 * The rotation angle determines the direction of the engine's thrust vector.
 * </p>
 *
 * <p>
 * This class is designed to be used as a manoeuvering engine within rocket
 * assemblies.
 * It implements the {@link MotorMount} interface for compatibility with rocket
 * motor systems.
 * </p>
 *
 * <p>
 * Manoeuvering engines are critical components that contribute to rocket
 * orientation and control.
 * </p>
 *
 * <p>
 * When setting the velocity of the manoeuvering engine using
 * {@link #setVelocity(double)},
 * the specified velocity must be positive (greater than zero).
 * </p>
 *
 * @author Gleb Kiva
 * @see InnerComponent
 * @see InnerEngine
 * @see MotorMount
 */
public class ManoeuveringEngine extends InnerComponent implements MotorMount {
    /**
     * The thrust vector of the manoeuvering engine (direction of movement).
     */
    private final Vector vector;

    /**
     * The current velocity of the manoeuvering engine (in units per second).
     */
    private double velocity;

    /**
     * Constructs a manoeuvering engine with the specified base information and
     * rotation angle.
     *
     * @param componentBase The base information of the manoeuvering engine.
     * @param rotation      The rotation angle (in radians) that determines the
     *                      thrust direction.
     * @throws IllegalArgumentException If the rotation angle is invalid.
     */
    public ManoeuveringEngine(final ComponentBase componentBase, final double rotation)
            throws IllegalArgumentException {
        super(componentBase);
        // Calculate thrust vector based on rotation angle (direction of thrust)
        this.vector = new Vector(Math.sin(rotation), Math.cos(rotation), 0);
        setVelocity(0); // Default velocity
    }

    /**
     * Calculates the movement vector of the manoeuvering engine based on its
     * velocity and direction.
     *
     * @return The movement vector of the manoeuvering engine.
     */
    @Override
    public Vector calculateMovement() {
        return Vector.multiply(this.vector, velocity);
    }

    /**
     * Retrieves the thrust vector of the manoeuvering engine (direction of
     * movement).
     *
     * @return The thrust vector of the manoeuvering engine.
     */
    @Override
    public Vector getVector() {
        return vector;
    }

    /**
     * Sets the velocity of the manoeuvering engine.
     *
     * @param velocity The velocity to set (in units per second).
     * @throws NonPositiveNumberException If the velocity is not positive.
     */
    @Override
    public void setVelocity(double velocity) throws NonPositiveNumberException {
        this.velocity = RequirementHandler.requirePositive(velocity);
        System.out.printf("Скорость %s успешно назначена на %f\n", name, this.velocity);
    }

    /**
     * Retrieves the current velocity of the manoeuvering engine.
     *
     * @return The current velocity of the manoeuvering engine (in units per
     *         second).
     */
    @Override
    public double getVelocity() {
        return velocity;
    }
}
