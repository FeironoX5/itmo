package components.mass;

import components.inner.InnerComponent;
import enums.ComponentType;
import enums.Material;

public class MassComponent extends InnerComponent {
    public MassComponent(String name, double mass, Material material) {
        super(name, mass, material);
    }
}
