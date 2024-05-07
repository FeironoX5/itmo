package teapot_rocket.utils;

import java.util.Objects;

import teapot_rocket.utils.enums.Material;

/**
 * The {@code ComponentBase} class represents the basic attributes of a
 * component used in rocket construction. It encapsulates properties such as
 * name, dimensions (width and height), weight, and material.
 * 
 * This class is immutable, meaning its fields are set once during construction
 * and cannot be changed afterwards.
 * 
 * @author Gleb Kiva
 * @see teapot_rocket.components.Component
 */
public final class ComponentBase {
    /**
     * The name of the component.
     */
    public final String name;

    /**
     * The width of the component in meters.
     */
    public final double width;

    /**
     * The height of the component in meters.
     */
    public final double height;

    /**
     * The weight of the component in kilograms.
     */
    public final double weight;

    /**
     * The material of the component, specified by {@link Material}.
     */
    public final Material material;

    /**
     * Constructs a new {@code ComponentBase} with the specified properties.
     * 
     * @param name     The name of the component (must not be empty or null)
     * @param width    The width of the component in meters (must be positive)
     * @param height   The height of the component in meters (must be positive)
     * @param weight   The weight of the component in kilograms (must be positive)
     * @param material The material of the component, specified by {@link Material}
     *                 (must not be null)
     * @throws IllegalArgumentException if any of the parameters are invalid
     * @throws NullPointerException     if any of the parameters are null
     */
    public ComponentBase(
            final String name,
            final double width, final double height, final double weight,
            final Material material)
            throws IllegalArgumentException, NullPointerException {
        this.name = RequirementHandler.requireNonEmptyString(name);
        this.width = RequirementHandler.requirePositive(width);
        this.height = RequirementHandler.requirePositive(height);
        this.weight = RequirementHandler.requirePositive(weight);
        this.material = Objects.requireNonNull(material);
    }
}
