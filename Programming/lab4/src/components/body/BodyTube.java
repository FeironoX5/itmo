package components.body;

import components.Vector;
import components.inner.InnerComponent;
import enums.Material;
import interfaces.MotorMount;
import interfaces.Rotatable;


public class BodyTube extends BodyComponent implements Rotatable {
    private final double wallThickness;
    private double rotation;

    public BodyTube(String name, double mass, Material material, InnerComponent[] inners, double height, double diameter, double wallThickness) {
        super(name, mass, material, inners, height, diameter);
        if (wallThickness <= 0) {
            // TODO error
        }
        this.wallThickness = wallThickness;
        this.rotation = 0;
    }

    @Override
    public Vector calculateMovement() {
        Vector vector = super.calculateMovement();
        return Vector.rotateZ(vector, rotation);
    }

    @Override
    public void rotate(double delta) {
        rotation = (rotation + delta + 180) % 360 - 180;
        System.out.printf("Поворот %s успешно назначен на %f градусов\n", getName(), rotation);
    }

    @Override
    public double getRotation() {
        return rotation;
    }

    public double getWallThickness() {
        return wallThickness;
    }
}
