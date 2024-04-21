package components.mass;

import enums.Material;
import exceptions.NaturalNumberException;
import exceptions.NumberException;
import exceptions.NameException;

public class Parachute extends MassComponent {
    private final double diameter;
    private final double weightCapacity;

    public Parachute(String name, double mass, Material material, double diameter, double weightCapacity)
            throws NameException, NumberException, NaturalNumberException {
        super(name, mass, material);
        if (diameter <= 0) {
            throw new NaturalNumberException("Неправильный диаметр компонента");
        }
        this.diameter = diameter;
        if (weightCapacity <= 0) {
            throw new NaturalNumberException("Неправильная грузоподъёмность компонента");
        }
        this.weightCapacity = weightCapacity;
    }

    public double getDiameter() {
        return diameter;
    }

    public double getWeightCapacity() {
        return weightCapacity;
    }
}
