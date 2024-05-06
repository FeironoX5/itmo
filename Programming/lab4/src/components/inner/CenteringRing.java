package components.inner;

import utils.ComponentBase;
import utils.RequirementHandler;
import utils.exceptions.EmptyStringException;
import utils.exceptions.NonPositiveNumberException;

public class CenteringRing extends InnerComponent {
    public final double wallThickness;

    public CenteringRing(final ComponentBase componentBase,
            final double wallThickness)
            throws EmptyStringException, NonPositiveNumberException {
        super(componentBase);
        this.wallThickness = RequirementHandler.requirePositive(wallThickness);
    }

}
