package components.mass;

import components.inner.InnerComponent;
import utils.ComponentBase;
import utils.exceptions.EmptyStringException;
import utils.exceptions.NonPositiveNumberException;

public class MassComponent extends InnerComponent {
    public MassComponent(final ComponentBase componentBase)
            throws NonPositiveNumberException, EmptyStringException {
        super(componentBase);
    }
}
