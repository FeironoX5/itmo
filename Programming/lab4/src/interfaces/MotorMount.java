package interfaces;

import components.Vector;
import exceptions.NaturalNumberException;

public interface MotorMount {

    void setVelocity(double velocity) throws NaturalNumberException;

    double getVelocity();

    Vector getVector();

    Vector calculateMovement();
}
