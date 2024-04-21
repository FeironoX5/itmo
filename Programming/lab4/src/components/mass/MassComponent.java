package components.mass;

import components.inner.InnerComponent;
import enums.Material;
import exceptions.NameException;
import exceptions.NaturalNumberException;
import exceptions.NumberException;

public class MassComponent extends InnerComponent {
    public MassComponent(String name, double mass, Material material)
            throws NameException, NumberException, NaturalNumberException {
        super(name, mass, material);
    }
}
