package components.assembly;

import components.Component;
import utils.ComponentBase;

/**
 * Represents an assembly component used in rocket construction.
 *
 * <p>
 * This class extends {@link Component} and is intended for components
 * involved in rocket assembly.
 * </p>
 *
 * @author Gleb Kiva
 * @see Component
 */
public abstract class AssemblyComponent extends Component {
    /**
     * Constructs an assembly component with the specified base information.
     *
     * @param componentBase The base information of the assembly component.
     * @throws IllegalArgumentException If componentBase is null.
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
