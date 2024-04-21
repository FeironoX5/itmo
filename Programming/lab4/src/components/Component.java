package components;

import enums.ComponentType;
import enums.Material;
import exceptions.NameException;
import exceptions.NaturalNumberException;
import exceptions.NumberException;

public abstract class Component {
    private final String name;
    private final double mass;
    private final ComponentType type;
    public final Material material;

    public Component(String name, double mass, Material material, ComponentType type) throws NameException, NumberException, NaturalNumberException {
        if (name.isEmpty()) {
            throw new NameException("Неправильное имя компонента");
        }
        this.name = name;
        if (mass <= 0) {
            throw new NaturalNumberException("Неправильная масса компонента");
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
