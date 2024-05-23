package teapot.rocket.utils.interfaces;

/**
 * The {@code Physical} interface represents physical dimensions and weight of
 * an object.
 * <p>
 * Implementing classes define methods to retrieve width, height, and weight of
 * an object.
 * </p>
 * <p>
 * This interface provides methods to retrieve the physical dimensions (width
 * and height) and weight of an object.
 * </p>
 * 
 * @author Gleb Kiva
 */
public interface Physical {
    /**
     * Returns the width dimension of the object.
     *
     * @return The width dimension
     */
    double getWidth();

    /**
     * Returns the height dimension of the object.
     *
     * @return The height dimension
     */
    double getHeight();

    /**
     * Returns the weight of the object.
     *
     * @return The weight of the object
     */
    double getWeight();
}
