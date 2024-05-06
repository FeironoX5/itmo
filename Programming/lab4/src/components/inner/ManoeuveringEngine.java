package components.inner;

import components.Vector;
import utils.ComponentBase;
import utils.RequirementHandler;
import utils.exceptions.NonPositiveNumberException;
import utils.interfaces.MotorMount;

public class ManoeuveringEngine extends InnerComponent implements MotorMount {
    private final Vector vector;
    private double velocity;

    public ManoeuveringEngine(final ComponentBase componentBase, final double rotation)
            throws IllegalArgumentException {
        super(componentBase);
        this.vector = new Vector(Math.sin(rotation), Math.cos(rotation), 0);
        setVelocity(0);
    }

    @Override
    public Vector calculateMovement() {
        return Vector.multiply(this.vector, velocity);
    }

    @Override
    public Vector getVector() {
        return vector;
    }

    @Override
    public void setVelocity(double velocity) throws NonPositiveNumberException {
        this.velocity = RequirementHandler.requirePositive(velocity);
        System.out.printf("Скорость %s успешно назначена на %f\n", name, this.velocity);
    }

    @Override
    public double getVelocity() {
        return velocity;
    }

}
