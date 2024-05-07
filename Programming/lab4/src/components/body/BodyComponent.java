package components.body;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import components.Component;
import components.inner.InnerComponent;
import utils.ComponentBase;
import utils.RequirementHandler;
import utils.Vector;
import utils.interfaces.MotorMount;

/**
 * Represents a body component used in rocket construction.
 *
 * <p>
 * This abstract class extends {@link Component} and serves as a base class
 * for various body components used in rockets. It defines common behavior
 * and properties of body components, such as calculating movement,
 * retrieving dimensions, and managing inner components.
 * </p>
 *
 * <p>
 * Subclasses of {@code BodyComponent} are expected to implement specific
 * types of body components with unique functionalities.
 * </p>
 *
 * @author Gleb Kiva
 * @see Component
 * @see InnerComponent
 * @see MotorMount
 */
public abstract class BodyComponent extends Component {
    private final LinkedList<InnerComponent> inners;

    /**
     * Constructs a body component with the specified base information
     * and inner components.
     *
     * @param componentBase The base information of the body component.
     * @param inners        The list of inner components contained within
     *                      this body component.
     * @throws IllegalArgumentException If the inner components list is empty or
     *                                  null.
     */
    public BodyComponent(final ComponentBase componentBase,
            final LinkedList<InnerComponent> inners)
            throws IllegalArgumentException {
        super(componentBase);
        this.inners = RequirementHandler.requireNonEmptyArray(inners);
    }

    /**
     * Calculates the movement vector of the body component.
     *
     * <p>
     * This method iterates over the inner components of the body component
     * and accumulates their movement vectors. If an inner component implements
     * the {@link MotorMount} interface, its movement vector is added to the total.
     * </p>
     *
     * @return The movement vector representing the overall movement
     *         of the body component.
     */
    public Vector calculateMovement() {
        Vector vector = new Vector(0, 0, 0);
        for (InnerComponent c : getInners()) {
            if (c instanceof MotorMount) {
                vector.set(Vector.add(
                        vector,
                        ((MotorMount) c).calculateMovement()));
            }
        }
        return vector;
    }

    /**
     * Retrieves the list of inner components contained within this body component.
     *
     * @return The list of inner components.
     */
    public LinkedList<InnerComponent> getInners() {
        return inners;
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * This method calculates the maximum width among the inner components
     * of the body component.
     * </p>
     *
     * @return The maximum width of the body component based on its inner
     *         components.
     */
    @Override
    public double getWidth() {
        return Collections.max(
                inners,
                Comparator.comparing(InnerComponent::getWidth))
                .getWidth();
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * This method calculates the maximum height among the inner components
     * of the body component.
     * </p>
     *
     * @return The maximum height of the body component based on its inner
     *         components.
     */
    @Override
    public double getHeight() {
        return Collections.max(
                inners,
                Comparator.comparing(InnerComponent::getHeight))
                .getHeight();
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * This method calculates the total weight of the body component by
     * summing the weights of its inner components and adding its base weight.
     * </p>
     *
     * @return The total weight of the body component.
     */
    @Override
    public double getWeight() {
        return inners.stream()
                .mapToDouble(InnerComponent::getWeight)
                .sum() + this.weight;
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * Returns a string representation of the body component, including its
     * name and a formatted representation of its inner components.
     * </p>
     *
     * @return A string representation of the body component.
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder(
                String.format("| |_Основной %s\n", name));
        for (InnerComponent c : getInners()) {
            res.append(c);
        }
        return res.toString();
    }
}
