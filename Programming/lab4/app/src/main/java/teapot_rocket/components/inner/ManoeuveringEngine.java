package teapot_rocket.components.inner;

import teapot_rocket.utils.ComponentBase;
import teapot_rocket.utils.RequirementHandler;
import teapot_rocket.utils.Vector;
import teapot_rocket.utils.exceptions.NonPositiveNumberException;
import teapot_rocket.utils.interfaces.MotorMount;

/**
 * Represents an manoeuvering engine component used in rocket construction.
 *
 * <p>
 * This class extends {@link InnerComponent} and implements the
 * {@link MotorMount} interface. It represents a rocket engine
 * component <b>located in rocket's head.</b>
 * </p>
 *
 * <p>
 * The manoeuvering engine can calculate its movement vector based on velocity
 * and direction. It provides methods to set and retrieve the velocity of the
 * engine.
 * </p>
 *
 * <p>
 * To create an manoeuvering engine, specify its base information and wall
 * thickness. The velocity of the engine can be adjusted using the
 * {@link #setVelocity(double)} method during the flight.
 * </p>
 *
 * @author Gleb Kiva
 * @see InnerEngine
 */
public final class ManoeuveringEngine extends InnerComponent implements MotorMount {
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
     * @param rotation      The rotation angle (in degrees) that determines the
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
