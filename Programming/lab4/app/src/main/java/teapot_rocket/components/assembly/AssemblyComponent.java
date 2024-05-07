package teapot_rocket.components.assembly;

import teapot_rocket.components.Component;
import teapot_rocket.utils.ComponentBase;

/**
 * Represents an assembly component used in
 * {@link teapot_rocket.components.Rocket} construction.
 *
 * @author Gleb Kiva
 * @see teapot_rocket.components.Rocket
 */
public abstract class AssemblyComponent extends Component {
    /**
     * Constructs an assembly component with the specified base information.
     *
     * @param componentBase The base information of the assembly component.
     * @throws IllegalArgumentException If componentBase is invalid.
     */
    public AssemblyComponent(final ComponentBase componentBase)
            throws IllegalArgumentException {
        super(componentBase);
    }

    /**
     * Returns a string representation of the assembly component.
     *
     * @return A string representation of the assembly component.
     */
    @Override
    public String toString() {
        return String.format("|_Сборочный %s\n", name);
    }
}
