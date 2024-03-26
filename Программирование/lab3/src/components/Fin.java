package components;

import components.inner.InnerComponent;
import enums.ComponentType;
import enums.FinShape;
import enums.Material;

public class Fin extends Component {
    private final FinShape shape;
    private final double length;

    public Fin(String name, double mass, Material material, FinShape shape, double length) {
        super(name, mass, material, ComponentType.OUTER);
        this.shape = shape;
        if (length <= 0) {
            // TODO error
        }
        this.length = length;
    }

    public FinShape getShape() {
        return shape;
    }

    public double getLength() {
        return length;
    }

    @Override
    public String toString() {
        String res = "";
        res += String.format("| | | |_%s %s\n", getType(), getName());
        return res;
    }
}
