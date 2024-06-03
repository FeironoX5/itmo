package teapot.models.rocket.components.inner;

import teapot.models.rocket.utils.ComponentBase;
import teapot.utils.RequirementHandler;

/**
 * Represents a centering ring, which is an inner component used in rocket
 * construction to provide structural support and alignment within the rocket
 * body.
 * <p>
 * This class extends {@link InnerComponent}, inheriting common functionality
 * and behavior from inner components.
 * </p>
 * 
 * @author Gleb Kiva
 */
public final class CenteringRing extends InnerComponent {

    /** The wall thickness of the centering ring. */
    public final double wallThickness;

    /**
     * Constructs a {@code CenteringRing} object with the specified component base
     * and wall thickness.
     *
     * @param componentBase The base information of the centering ring.
     * @param wallThickness The wall thickness of the centering ring.
     * @throws IllegalArgumentException If the wall thickness is not positive.
     */
    public CenteringRing(final ComponentBase componentBase,
            final double wallThickness)
            throws IllegalArgumentException {
        super(componentBase);
        this.wallThickness = RequirementHandler.requirePositive(wallThickness);
    }

}
