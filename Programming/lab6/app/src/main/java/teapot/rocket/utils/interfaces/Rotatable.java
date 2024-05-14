package teapot.rocket.utils.interfaces;

/**
 * The {@code Rotatable} interface is designed for components that can be
 * rotated.
 * Implementing classes define methods to get the current rotation angle in
 * degrees and to rotate the component by a specified angle.
 * 
 * This interface provides a way to interact with rotatable components,
 * allowing them to be rotated by a specified angle.
 * 
 * @author Gleb Kiva
 * @see teapot.rocket.components.body.BodyTube
 */
public interface Rotatable {
    /**
     * Returns the current rotation angle in degrees.
     *
     * @return The rotation angle of the component in degrees
     */
    double getRotation();

    /**
     * Rotates the component by the specified angle in degrees.
     *
     * @param angle The angle in degrees by which to rotate the component
     */
    void rotate(double angle);
}
