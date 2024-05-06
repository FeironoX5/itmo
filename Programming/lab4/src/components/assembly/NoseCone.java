package components.assembly;

import utils.ComponentBase;
import utils.RequirementHandler;
import utils.enums.NoseConeShape;
import utils.exceptions.EmptyStringException;
import utils.exceptions.NonPositiveNumberException;

public class NoseCone extends AssemblyComponent {
    public final double wallThickness;
    public final NoseConeShape shape;

    public NoseCone(final ComponentBase componentBase, final double wallThickness, final NoseConeShape shape)
            throws EmptyStringException, NonPositiveNumberException {
        super(componentBase);
        this.wallThickness = RequirementHandler.requirePositive(wallThickness);
        this.shape = shape;
    }

}
