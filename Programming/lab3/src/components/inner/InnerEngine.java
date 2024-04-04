package components.inner;

import components.Vector;
import enums.Material;
import interfaces.MotorMount;

public class InnerEngine extends InnerComponent implements MotorMount {
    private final Vector vector;
    private final double diameter;
    private final double wallThickness;
    private double velocity;

    public InnerEngine(String name, double mass, Material material, double diameter, double wallThickness) {
        super(name, mass, material);
        if (diameter <= 0) {
            // TODO error
        }
        this.diameter = diameter;
        if (wallThickness <= 0) {
            // TODO error
        }
        this.wallThickness = wallThickness;
        this.vector = new Vector(0, 0, -1);
        setVelocity(0);
    }

    @Override
    public Vector calculateMovement() {
        return Vector.multiply(this.vector, velocity);
    }

    @Override
    public void setVelocity(double velocity) {
        if (velocity < 0) {
            // TODO add error
        }
        this.velocity = velocity;
        System.out.printf("Скорость %s успешно назначена на %f\n", getName(), this.velocity);
    }

    @Override
    public double getVelocity() {
        return velocity;
    }

    public double getDiameter() {
        return diameter;
    }

    public double getWallThickness() {
        return wallThickness;
    }

    @Override
    public Vector getVector() {
        return vector;
    }
}
