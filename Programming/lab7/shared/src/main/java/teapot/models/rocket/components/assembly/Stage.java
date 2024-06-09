package teapot.models.rocket.components.assembly;

import teapot.models.rocket.components.body.BodyComponent;
import teapot.models.rocket.utils.ComponentBase;
import teapot.models.rocket.utils.Vector;
import teapot.utils.handlers.RequirementHandler;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * Represents a stage assembly component used in rocket construction.
 *
 * <p>
 * This class extends {@link AssemblyComponent} and represents a stage
 * of a rocket, consisting of
 * {@link BodyComponent} instances.
 * </p>
 *
 * <p>
 * To safely extend this class, subclasses should ensure that overridden
 * methods maintain the expected behavior and adhere to the contract defined
 * in this class.
 * </p>
 *
 * <p>
 * For example, when subclassing {@code Stage}, ensure that overridden
 * methods such as {@link #getWidth()}, {@link #getHeight()}, and
 * {@link #getWeight()} correctly handle the calculation.
 * </p>
 *
 * @author Gleb Kiva
 * @see BodyComponent
 */
public final class Stage extends AssemblyComponent {
    /**
     * Body components within this stage.
     */
    private final LinkedList<BodyComponent> body;

    /**
     * Constructs a stage with the specified base information and body components.
     *
     * @param componentBase The base information of the stage.
     * @param body          The list of
     *                      {@link BodyComponent}
     *                      instances within the stage.
     *                      Represents body components that compose the stage.
     * @throws IllegalArgumentException If component configuration is invalid.
     */
    public Stage(final ComponentBase componentBase,
                 final LinkedList<BodyComponent> body)
            throws IllegalArgumentException {
        super(componentBase);
        this.body = RequirementHandler.requireNonEmptyArray(body);
    }

    /**
     * Retrieves body components of the stage.
     *
     * @return The list of {@link BodyComponent}
     * instances within the stage.
     * Represents body components that compose the stage.
     */
    public LinkedList<BodyComponent> getBody() {
        return body;
    }

    /**
     * Adds the body component to the body of the stage.
     */
    public void addStage(BodyComponent bodyComponent) {
        this.body.add(bodyComponent);
    }


    /**
     * Calculates the movement vector of the stage based on its body components.
     *
     * @return The movement vector which stage provides.
     */
    public Vector calculateMovement() {
        Vector vector = new Vector(0, 0, 0); // FIXME
        for (BodyComponent c : getBody()) {
            vector.set(Vector.add(vector, c.calculateMovement()));
        }
        return vector;
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * This method calculates the maximum width among body components
     * in the stage.
     * </p>
     *
     * @return The maximum width of the stage based on its body components.
     */
    @Override
    public double getWidth() {
        return Collections.max(
                        body,
                        Comparator.comparing(BodyComponent::getWidth))
                .getWidth();
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * This method calculates the total height of the stage by summing the
     * heights of all body components.
     * </p>
     *
     * @return The total height of the stage based on its body components.
     */
    @Override
    public double getHeight() {
        return body.stream().mapToDouble(BodyComponent::getHeight).sum();
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * This method calculates the total weight of the stage by summing the
     * weights of all body components and adding the base weight of the stage.
     * </p>
     *
     * @return The total weight of the stage based on its body components.
     */
    @Override
    public double getWeight() {
        return body.stream()
                .mapToDouble(BodyComponent::getWeight)
                .sum() + this.weight;
    }

    /**
     * Returns a string representation of the stage.
     *
     * <p>
     * The string includes the name of the stage followed by the string
     * representations of its body components, separated by new lines.
     * </p>
     *
     * @return A string representation of the stage.
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder(String.format("%s\n", name));
        for (BodyComponent c : getBody()) {
            res.append(c);
        }
        return res.toString();
    }
}
