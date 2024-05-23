package teapot.rocket.components.mass;

import teapot.rocket.components.inner.InnerComponent;
import teapot.rocket.utils.ComponentBase;

/**
 * Represents a mass component used in rocket construction.
 *
 * <p>
 * This class extends {@link InnerComponent} and represents a non-functional
 * component designed to contribute to the overall mass of the rocket.
 * </p>
 *
 * @author Gleb Kiva
 */
public class MassComponent extends InnerComponent {
    /**
     * Constructs a mass component with the specified base information.
     *
     * @param componentBase The base information of the mass component.
     * @throws IllegalArgumentException If the {@code componentBase} is null or
     *                                  invalid.
     */
    public MassComponent(final ComponentBase componentBase)
            throws IllegalArgumentException {
        super(componentBase);
    }

}
