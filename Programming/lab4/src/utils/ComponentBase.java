package utils;

import java.util.Objects;

import utils.enums.Material;

public final class ComponentBase {
    public final String name;
    public final double width;
    public final double height;
    public final double weight;
    public final Material material;

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
