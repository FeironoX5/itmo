package utils;

import utils.enums.Material;

public class ComponentBase {
    public final String name;
    public final double width;
    public final double height;
    public final double weight;
    public final Material material;

    public ComponentBase(
            final String name,
            final double width, final double height, final double weight,
            final Material material) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.weight = weight;
        this.material = material;
    }

}
