package components.body;

import components.Component;
import components.Vector;
import components.inner.InnerComponent;
import enums.ComponentType;
import enums.Material;
import interfaces.MotorMount;

public abstract class BodyComponent extends Component {
    private final InnerComponent[] inners;
    private final double height;
    private final double diameter;

    public BodyComponent(String name, double mass, Material material, InnerComponent[] inners, double height, double diameter) {
        super(name, mass, material, ComponentType.BODY);
        this.inners = inners;
        if (height <= 0) {
            // TODO error
        }
        this.height = height;
        if (diameter <= 0) {
            // TODO error
        }
        this.diameter = diameter;
    }

    public Vector calculateMovement() {
        Vector vector = new Vector(0, 0, 0);
        for (InnerComponent c : getInners()) {
            if (c instanceof MotorMount) {
                vector.set(Vector.add(vector, ((MotorMount) c).calculateMovement()));
            }
        }
        return vector;
    }

    public InnerComponent[] getInners() {
        return inners;
    }

    public double getHeight() {
        return height;
    }

    public double getDiameter() {
        return diameter;
    }

    @Override
    public String toString() {
        String res = "";
        res += String.format("| |_%s %s\n", getType(), getName());
        for (InnerComponent c : getInners()) {
            res += c.toString();
        }
        return res;
    }
}
