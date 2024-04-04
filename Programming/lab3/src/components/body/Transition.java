package components.body;

import components.inner.InnerComponent;
import enums.Material;
import enums.TransitionShape;

public class Transition extends BodyComponent {
    private final double innerDiameter;
    private final TransitionShape shape;

    public Transition(String name, double mass, Material material, InnerComponent[] inners, double height, double diameter, double innerDiameter, TransitionShape shape) {
        super(name, mass, material, inners, height, diameter);
        if (innerDiameter <= 0) {
            // TODO error
        }
        this.innerDiameter = innerDiameter;
        this.shape = shape;
    }

    public double getInnerDiameter() {
        return innerDiameter;
    }

    public TransitionShape getShape() {
        return shape;
    }
}
