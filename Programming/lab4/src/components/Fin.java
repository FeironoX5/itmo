package components;

import enums.ComponentType;
import enums.FinShape;
import enums.Material;
import exceptions.NameException;
import exceptions.NaturalNumberException;
import exceptions.NumberException;

public class Fin extends Component {
    private final FinShape shape;
    private final double length;

    public Fin(String name, double mass, Material material, FinShape shape, double length)
            throws NameException, NumberException {
        super(name, mass, material, ComponentType.OUTER);
        this.shape = shape;
        if (length <= 0) {
            throw new NaturalNumberException("Неправильная длина компонента");
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
