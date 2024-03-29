package components.inner;

import components.Component;
import components.body.BodyComponent;
import enums.ComponentType;
import enums.Material;

public abstract class InnerComponent extends Component {
    public InnerComponent(String name, double mass, Material material) {
        super(name, mass, material, ComponentType.INNER);
    }
    @Override
    public String toString() {
        String res = "";
        res += String.format("| | |_%s %s\n", getType(), getName());
        return res;
    }
}
