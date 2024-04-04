package interfaces;

import components.Vector;

public interface MotorMount {

    void setVelocity(double velocity);

    double getVelocity();

    Vector getVector();

    Vector calculateMovement();
}
