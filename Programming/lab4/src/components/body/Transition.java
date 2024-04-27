package components.body;

import components.inner.InnerComponent;
import utils.enums.Material;
import utils.enums.TransitionShape;
import utils.exceptions.NameException;
import utils.exceptions.NaturalNumberException;
import utils.exceptions.NumberException;

public class Transition extends BodyComponent {
    private final double innerDiameter;
    private final TransitionShape shape;

    public Transition(String name, double mass, Material material,
            InnerComponent[] inners, double height, double diameter, double innerDiameter, TransitionShape shape)
            throws NameException, NumberException, NaturalNumberException {
        super(name, mass, material, inners, height, diameter);
        if (innerDiameter <= 0) {
            throw new NaturalNumberException("Неправильный внутренний диаметр компонента");
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
