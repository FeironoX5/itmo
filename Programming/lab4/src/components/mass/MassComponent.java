package components.mass;

import components.inner.InnerComponent;
import utils.enums.Material;
import utils.exceptions.NameException;
import utils.exceptions.NaturalNumberException;
import utils.exceptions.NumberException;

public class MassComponent extends InnerComponent {
    public MassComponent(String name, double mass, Material material)
            throws NameException, NumberException, NaturalNumberException {
        super(name, mass, material);
    }
}
