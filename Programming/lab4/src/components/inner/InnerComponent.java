package components.inner;

import components.Component;
import utils.ComponentBase;
import utils.exceptions.EmptyStringException;
import utils.exceptions.NonPositiveNumberException;

public abstract class InnerComponent extends Component {
    public InnerComponent(final ComponentBase componentBase)
            throws EmptyStringException, NonPositiveNumberException {
        super(componentBase);
    }

    @Override
    public String toString() {
        String res = "";
        res += String.format("| | |_Внутренний%s %s\n", name);
        return res;
    }
}
