package components.inner;

import utils.ComponentBase;
import utils.RequirementHandler;

public class CenteringRing extends InnerComponent {
    public final double wallThickness;

    public CenteringRing(final ComponentBase componentBase,
            final double wallThickness)
            throws IllegalArgumentException {
        super(componentBase);
        this.wallThickness = RequirementHandler.requirePositive(wallThickness);
    }

}
