package components.assembly;

import components.Component;
import utils.ComponentBase;

public abstract class AssemblyComponent extends Component {
    public AssemblyComponent(final ComponentBase componentBase)
            throws IllegalArgumentException {
        super(componentBase);
    }

    @Override
    public String toString() {
        return String.format("|_Сборочный %s\n", name);
    }
}
