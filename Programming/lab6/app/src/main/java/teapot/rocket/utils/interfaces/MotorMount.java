package teapot.rocket.utils.interfaces;

import teapot.rocket.utils.Vector;
import teapot.rocket.utils.exceptions.NonPositiveNumberException;

/**
 * The {@code MotorMount} interface represents a rocket engine mount.
 * Implementing classes define methods to control engine power and retrieve
 * engine characteristics.
 * <p>
 * This interface provides methods to set and retrieve engine velocity,
 * retrieve the engine direction vector, and calculate the movement vector
 * produced by the engine.
 *
 * @author Gleb Kiva
 */
public interface MotorMount {
    /**
     * Sets the velocity of the engine.
     *
     * @param velocity The velocity to set (watt/thrust newtons)
     * @throws NonPositiveNumberException If the velocity value is non-positive
     */
    void setVelocity(double velocity) throws NonPositiveNumberException;

    /**
     * Retrieves the velocity of the engine.
     *
     * @return The velocity of the engine (watt/thrust newtons)
     */
    double getVelocity();

    /**
     * Retrieves the direction vector of the engine.
     *
     * @return The direction vector of the engine
     */
    Vector getVector();

    /**
     * Calculates the movement vector produced by the engine.
     *
     * @return The movement vector produced by the engine
     */
    Vector calculateMovement();
}
