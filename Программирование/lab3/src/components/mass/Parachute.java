package components.mass;

import enums.Material;

public class Parachute extends MassComponent {
    private final double diameter;
    private final double weightCapacity;

    public Parachute(String name, double mass, Material material, double diameter, double weightCapacity) {
        super(name, mass, material);
        if (diameter <= 0) {
            // TODO error
        }
        this.diameter = diameter;
        if (weightCapacity <= 0) {
            // TODO error
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
