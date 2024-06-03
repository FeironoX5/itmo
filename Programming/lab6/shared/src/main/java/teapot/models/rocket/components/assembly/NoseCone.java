package teapot.models.rocket.components.assembly;

import teapot.models.rocket.utils.ComponentBase;
import teapot.models.rocket.utils.enums.NoseConeShape;
import teapot.utils.RequirementHandler;

/**
 * Represents a nose cone assembly component used in rocket construction.
 * 
 * <p>
 * This class extends {@link AssemblyComponent} and represents
 * the nose cone of a rocket, characterized by its wall thickness
 * and shape.
 * </p>
 *
 * @author Gleb Kiva
 */
public final class NoseCone extends AssemblyComponent {
    /**
     * The wall thickness of the nose cone.
     */
    public final double wallThickness;
    /**
     * The shape of the nose cone.
     */
    public final NoseConeShape shape;

    /**
     * Constructs a nose cone with the specified base information,
     * wall thickness, and shape.
     *
     * @param componentBase The base information of the nose cone.
     * @param wallThickness The wall thickness of the nose cone.
     * @param shape         The shape of the nose cone.
     * @throws IllegalArgumentException If wallThickness is not positive.
     */
    public NoseCone(final ComponentBase componentBase,
            final double wallThickness, final NoseConeShape shape)
            throws IllegalArgumentException {
        super(componentBase);
        this.wallThickness = RequirementHandler.requirePositive(wallThickness);
        this.shape = shape;
    }
}
