package components.body;

import java.util.LinkedList;

import components.inner.InnerComponent;
import utils.ComponentBase;
import utils.RequirementHandler;
import utils.enums.TransitionShape;
import utils.exceptions.EmptyArrayException;
import utils.exceptions.EmptyStringException;
import utils.exceptions.NonPositiveNumberException;

public class Transition extends BodyComponent {
    public final double innerDiameter;
    public final TransitionShape shape;

    public Transition(final ComponentBase componentBase,
            final LinkedList<InnerComponent> inners,
            final double innerDiameter, final TransitionShape shape)
            throws EmptyStringException, NonPositiveNumberException, EmptyArrayException {
        super(componentBase, inners);
        this.innerDiameter = RequirementHandler.requirePositive(innerDiameter);
        this.shape = shape;
    }

}
