package components.inner;

import components.Component;
import enums.ComponentType;
import enums.Material;
import exceptions.NameException;
import exceptions.NaturalNumberException;
import exceptions.NumberException;

public abstract class InnerComponent extends Component {
    public InnerComponent(String name, double mass, Material material)
            throws NameException, NumberException, NaturalNumberException {
        super(name, mass, material, ComponentType.INNER);
    }

    @Override
    public String toString() {
        String res = "";
        res += String.format("| | |_%s %s\n", getType(), getName());
        return res;
    }
}
