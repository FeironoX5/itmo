package components.assembly;

import components.Component;
import utils.enums.ComponentType;
import utils.enums.Material;
import utils.exceptions.NameException;
import utils.exceptions.NumberException;

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
