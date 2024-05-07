package components.inner;

import components.Component;
import utils.ComponentBase;

public abstract class InnerComponent extends Component {
    public InnerComponent(final ComponentBase componentBase)
            throws IllegalArgumentException {
        super(componentBase);
    }

    @Override
    public String toString() {
        return String.format("| | |_Внутренний%s %s\n", name);
    }
}
