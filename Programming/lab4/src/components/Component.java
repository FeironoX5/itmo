package components;

import enums.ComponentType;
import enums.Material;

import java.util.Objects;

public abstract class Component {
    private final String name;
    private final double mass;
    private final ComponentType type;
    public final Material material;

    public Component(String name, double mass, Material material, ComponentType type) {
        if (name.isEmpty()) {
            // TODO error
        }
        this.name = name;
        if (mass <= 0) {
            // TODO error
        }
        this.mass = mass;
        this.material = material;
        this.type = type;
        System.out.printf("Создан новый %s '%s'\n", this.type, this.name);
    }

    public String getName() {
        return name;
    }

    public double getMass() {
        return mass;
    }

    public ComponentType getType() {
        return this.type;
    }
}
