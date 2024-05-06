package components.assembly;

import components.Component;
import utils.ComponentBase;
import utils.exceptions.EmptyStringException;
import utils.exceptions.NonPositiveNumberException;

public abstract class AssemblyComponent extends Component {
    public AssemblyComponent(final ComponentBase componentBase)
            throws EmptyStringException, NonPositiveNumberException {
        super(componentBase);
    }

    @Override
    public String toString() {
        return String.format("|_Сборочный %s\n", name);
    }
}
