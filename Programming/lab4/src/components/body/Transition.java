package components.body;

import java.util.LinkedList;

import components.inner.InnerComponent;
import utils.ComponentBase;
import utils.RequirementHandler;
import utils.enums.TransitionShape;

/**
 * Represents a transition component used in rocket body construction.
 *
 * <p>
 * This class extends {@link BodyComponent} and represents a transition section
 * of a rocket body that connects different diameter sections or shapes.
 * </p>
 *
 * <p>
 * The transition component contains inner components and specifies an inner
 * diameter
 * and transition shape for the rocket body.
 * </p>
 *
 * <p>
 * To create a transition, specify its base information, list of inner
 * components,
 * inner diameter, and transition shape.
 * </p>
 *
 * <p>
 * The inner diameter and transition shape are critical parameters for defining
 * the geometry and aerodynamic properties of the rocket body.
 * </p>
 *
 * <p>
 * This class is designed to be extended for specialized transition components
 * with specific shapes or functionalities.
 * </p>
 *
 * @author Gleb Kiva
 * @see BodyComponent
 */
public class Transition extends BodyComponent {
    /**
     * The inner diameter of the transition section.
     */
    public final double innerDiameter;

    /**
     * The shape of the transition section.
     */
    public final TransitionShape shape;

    /**
     * Constructs a transition with the specified base information, inner
     * components,
     * inner diameter, and transition shape.
     *
     * @param componentBase The base information of the transition.
     * @param inners        The list of {@link InnerComponent} contained within the
     *                      transition.
     * @param innerDiameter The inner diameter of the transition section.
     * @param shape         The shape of the transition section.
     * @throws IllegalArgumentException If the inner diameter is not positive.
     */
    public Transition(final ComponentBase componentBase,
            final LinkedList<InnerComponent> inners,
            final double innerDiameter, final TransitionShape shape)
            throws IllegalArgumentException {
        super(componentBase, inners);
        this.innerDiameter = RequirementHandler.requirePositive(innerDiameter);
        this.shape = shape;
    }
}
