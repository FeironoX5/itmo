package components;

import java.util.Objects;

import utils.ComponentBase;
import utils.enums.Material;
import utils.interfaces.Physical;

/**
 * The {@code Component} class is used for building rockets.
 * It should be implemented as one of the following classes:
 *
 * <pre>
 * Component
 *         | -BodyComponent
 *         | -AssemblyComponent
 *         | -InnerComponent
 * // TODO add OuterComponent as a separate class
 * </pre>
 *
 * <p>
 * The width, height, and weight represent the physical dimensions and mass
 * of the component.
 * </p>
 *
 * @author Gleb Kiva
 */
public abstract class Component implements Physical {
    /**
     * The name of the component, describing its application area if possible.
     */
    public final String name;
    /**
     * The width of the component.
     */
    public final double width;
    /**
     * The height of the component.
     */
    public final double height;
    /**
     * The weight of the component.
     */
    public final double weight;
    /**
     * The material of the component.
     *
     * @see Material
     */
    public final Material material;

    /**
     * Constructs a {@code Component} object,
     * validating the {@code name} and {@code mass} fields.
     *
     * @param componentBase The base information of the component.
     * @throws IllegalArgumentException If componentBase is null.
     */
    public Component(final ComponentBase componentBase)
            throws IllegalArgumentException {
        Objects.requireNonNull(componentBase);
        this.name = componentBase.name;
        this.width = componentBase.width;
        this.height = componentBase.height;
        this.weight = componentBase.weight;
        this.material = componentBase.material;
        System.out.printf("Created a new '%s'\n", this.name);
    }

    /**
     * Gets the weight of the component.
     *
     * <p>
     * This method can be implemented by subclasses to calculate the
     * weight of specific component types, for example example,
     * considering also the weight of the component children.
     * </p>
     *
     * @return The weight of the component.
     * @see Stage
     * @see BodyComponent
     */
    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public double getWeight() {
        return weight;
    }

}
