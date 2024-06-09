package teapot.models.rocket.components.assembly;

import teapot.models.rocket.Rocket;
import teapot.models.rocket.components.Component;
import teapot.models.rocket.utils.ComponentBase;

/**
 * Represents an assembly component used in
 * {@link Rocket} construction.
 *
 * @author Gleb Kiva
 * @see Rocket
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
