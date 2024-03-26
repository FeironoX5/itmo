package components.inner;

import enums.Material;
import interfaces.MotorMount;

public class CenteringRing extends InnerComponent {
    private final double diameter;
    private final double wallThickness;

    public CenteringRing(String name, double mass, Material material, double diameter, double wallThickness) {
        super(name, mass, material);
        if (diameter <= 0) {
            // TODO error
        }
        this.diameter = diameter;
        if (wallThickness <= 0) {
            // TODO error
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
