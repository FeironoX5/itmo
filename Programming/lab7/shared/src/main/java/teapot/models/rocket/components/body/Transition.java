package teapot.models.rocket.components.body;

import java.util.LinkedList;

import teapot.models.rocket.components.inner.InnerComponent;
import teapot.models.rocket.utils.ComponentBase;
import teapot.models.rocket.utils.enums.TransitionShape;
import teapot.utils.handlers.RequirementHandler;

/**
 * Represents a transition component used in rocket body construction.
 *
 * <p>
 * This class extends {@link BodyComponent}
 * and represents a transition section
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
 * The inner diameter and transition shape are critical parameters for defining
 * the geometry and aerodynamic properties of the rocket body.
 * </p>
 *
 * @author Gleb Kiva
 * @see BodyComponent
 */
public final class Transition extends BodyComponent {
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
