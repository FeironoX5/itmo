package components.inner;

import enums.Material;
import exceptions.NameException;
import exceptions.NaturalNumberException;
import exceptions.NumberException;
import interfaces.MotorMount;

public class CenteringRing extends InnerComponent {
    private final double diameter;
    private final double wallThickness;

    public CenteringRing(String name, double mass, Material material, double diameter, double wallThickness)
            throws NameException, NumberException, NaturalNumberException {
        super(name, mass, material);
        if (diameter <= 0) {
            throw new NaturalNumberException("Неправильный диаметр компонента");
        }
        this.diameter = diameter;
        if (wallThickness <= 0) {
            throw new NaturalNumberException("Неправильная толщина стенок компонента");
        }
        this.wallThickness = wallThickness;
    }

    public double getDiameter() {
        return diameter;
    }

    public double getWallThickness() {
        return wallThickness;
    }
}
