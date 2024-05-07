package components.mass;

import components.inner.InnerComponent;
import utils.ComponentBase;

/**
 * Represents a mass component used in rocket construction.
 *
 * <p>
 * This class extends {@link InnerComponent} and represents a component designed
 * to contribute to the overall mass of the rocket.
 * </p>
 *
 * <p>
 * The {@code MassComponent} class is used to model and incorporate mass-related
 * properties into the rocket's internal structure.
 * </p>
 *
 * <p>
 * To create a {@code MassComponent}, specify its base information using a
 * {@link ComponentBase} object.
 * </p>
 *
 * <p>
 * This class is designed to be used as a generic component within rocket assemblies,
 * providing mass-related characteristics to the rocket's internal structure.
 * </p>
 *
 * <p>
 * The {@link #toString()} method generates a string representation of the mass component.
 * </p>
 *
 * <p>
 * When creating instances of {@code MassComponent}, ensure that the provided
 * {@link ComponentBase} object is valid and contains necessary information
 * about the component.
 * </p>
 *
 * @author Gleb Kiva
 * @see InnerComponent
 * @see ComponentBase
 */
public class MassComponent extends InnerComponent {

    /**
     * Constructs a mass component with the specified base information.
     *
     * @param componentBase The base information of the mass component.
     * @throws IllegalArgumentException If the {@code componentBase} is null or invalid.
     */
    public MassComponent(final ComponentBase componentBase)
            throws IllegalArgumentException {
        super(componentBase);
    }

}
