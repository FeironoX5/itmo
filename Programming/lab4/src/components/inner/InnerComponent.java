package components.inner;

import components.Component;
import utils.ComponentBase;

/**
 * Represents an inner component used in rocket construction.
 *
 * <p>
 * This class extends {@link Component} and serves as a base class for various
 * internal components used within a rocket structure.
 * </p>
 *
 * <p>
 * Inner components are critical elements inside a rocket and can include
 * specialized components such as motor mounts, sensors, or payload compartments.
 * </p>
 *
 * <p>
 * To create a specific type of inner component, extend this class and implement
 * the necessary functionality.
 * </p>
 *
 * <p>
 * This class provides basic functionality for inner components and can be extended
 * for custom behaviors or properties.
 * </p>
 *
 * @author GlebKiva
 * @see Component
 * @see BodyComponent
 */
public abstract class InnerComponent extends Component {
    /**
     * Constructs an inner component with the specified base information.
     *
     * @param componentBase The base information of the inner component.
     * @throws IllegalArgumentException If the componentBase is null.
     */
    public InnerComponent(final ComponentBase componentBase)
            throws IllegalArgumentException {
        super(componentBase);
    }

    /**
     * Returns a string representation of the inner component.
     *
     * @return A string representing the inner component.
     */
    @Override
    public String toString() {
        return String.format("| | |_Внутренний%s %s\n", name);
    }
}
