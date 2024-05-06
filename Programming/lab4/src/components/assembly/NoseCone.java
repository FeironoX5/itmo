package components.assembly;

import utils.ComponentBase;
import utils.RequirementHandler;
import utils.enums.NoseConeShape;

public class NoseCone extends AssemblyComponent {
    public final double wallThickness;
    public final NoseConeShape shape;

    public NoseCone(final ComponentBase componentBase,
            final double wallThickness, final NoseConeShape shape)
            throws IllegalArgumentException {
        super(componentBase);
        this.wallThickness = RequirementHandler.requirePositive(wallThickness);
        this.shape = shape;
    }

}
