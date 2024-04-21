package components.assembly;

import components.body.BodyComponent;
import components.Component;
import enums.ComponentType;
import enums.Material;
import exceptions.NameException;
import exceptions.NaturalNumberException;
import exceptions.NumberException;

public abstract class AssemblyComponent extends Component {
    public AssemblyComponent(String name, double mass, Material material)
            throws NameException, NumberException {
        super(name, mass, material, ComponentType.ASSEMBLY);
    }

    @Override
    public String toString() {
        String res = "";
        res += String.format("|_%s %s\n", getType(), getName());
        return res;
    }
}
