package components.assembly;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import components.Vector;
import components.body.BodyComponent;
import utils.ComponentBase;
import utils.RequirementHandler;

/**
 * Represents a stage assembly component used in rocket construction.
 *
 * <p>
 * This class extends {@link AssemblyComponent} and represents a stage
 * of a rocket, consisting of {@link BodyComponent}s.
 * </p>
 *
 * <p>
 * A stage is composed of body components that form part of the rocket's
 * structure. It provides methods to calculate movement vectors and retrieve
 * dimensions and weight.
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
 * {@link #getWeight()} correctly handle the calculation and aggregation of
 * dimensions and weight based on the specific implementation of body
 * components within the stage.
 * </p>
 *
 * @author Gleb Kiva
 * @see AssemblyComponent
 * @see BodyComponent
 */
public class Stage extends AssemblyComponent {
    /**
     * The body components within this stage.
     */
    private final LinkedList<BodyComponent> body;

    /**
     * Constructs a stage with the specified base information and body components.
     *
     * @param componentBase The base information of the stage.
     * @param body          The list of {@link BodyComponent}s within the stage.
     *                      Represents the body components that compose the stage.
     * @throws IllegalArgumentException If body is empty or null.
     */
    public Stage(final ComponentBase componentBase,
            final LinkedList<BodyComponent> body)
            throws IllegalArgumentException {
        super(componentBase);
        this.body = RequirementHandler.requireNonEmptyArray(body);
    }

    /**
     * Retrieves the body components of the stage.
     *
     * @return The list of {@link BodyComponent}s within the stage.
     *         Represents the body components that compose the stage.
     */
    public LinkedList<BodyComponent> getBody() {
        return body;
    }

    /**
     * Calculates the movement vector of the stage based on its body components.
     *
     * @return The movement vector of the stage.
     */
    public Vector calculateMovement() {
        Vector vector = new Vector(0, 0, 0);
        for (BodyComponent c : getBody()) {
            vector.set(Vector.add(vector, c.calculateMovement()));
        }
        return vector;
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * This method calculates the maximum width among the body components
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
