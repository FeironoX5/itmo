package components.inner;

import components.Vector;
import enums.Material;
import exceptions.NameException;
import exceptions.NaturalNumberException;
import exceptions.NumberException;
import interfaces.MotorMount;

public class ManoeuvringEngine extends InnerComponent implements MotorMount {
    private Vector vector;
    private double velocity;

    public ManoeuvringEngine(String name, double mass, Material material, double rotation)
            throws NameException, NumberException, NaturalNumberException {
        super(name, mass, material);
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
    public void setVelocity(double velocity) throws NaturalNumberException {
        if (velocity < 0) {
            throw new NaturalNumberException("Неправильная скорость двигателя");
        }
        this.velocity = velocity;
        System.out.printf("Скорость %s успешно назначена на %f\n", getName(), this.velocity);
    }

    @Override
    public double getVelocity() {
        return velocity;
    }

}
