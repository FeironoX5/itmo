package components.inner;

import components.Vector;
import utils.ComponentBase;
import utils.RequirementHandler;
import utils.exceptions.NonPositiveNumberException;
import utils.interfaces.MotorMount;

public class InnerEngine extends InnerComponent implements MotorMount {
    private final Vector vector;
    public final double wallThickness;
    private double velocity;

    public InnerEngine(final ComponentBase componentBase,
            final double wallThickness)
            throws IllegalArgumentException {
        super(componentBase);
        this.wallThickness = RequirementHandler.requirePositive(wallThickness);
        this.vector = new Vector(0, 0, -1);
        setVelocity(0);
    }

    @Override
    public Vector calculateMovement() {
        return Vector.multiply(this.vector, velocity);
    }

    @Override
    public void setVelocity(double velocity) throws NonPositiveNumberException {
        this.velocity = RequirementHandler.requirePositive(velocity);
    }

    @Override
    public double getVelocity() {
        return velocity;
    }

    @Override
    public Vector getVector() {
        return vector;
    }
}
