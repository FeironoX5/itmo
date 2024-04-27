package components;

import utils.enums.ComponentType;
import utils.enums.FinShape;
import utils.enums.Material;
import utils.exceptions.NameException;
import utils.exceptions.NaturalNumberException;
import utils.exceptions.NumberException;

/**
 * {@code Fin} реализует {@link Component},
 * представляя собой стабилизатор на хвосте ракеты,
 * который крепится к оперению {@link components.inner.Trapezoidal}.
 *
 * @author Gleb Kiva
 */
public class Fin extends Component {
    /**
     * Форма стабилизатора.
     */
    private final FinShape shape;
    /**
     * Длина стабилизатора.
     */
    private final double length;

    /**
     * Создаёт {@code Fin},
     * которая проверяет введенные данные
     * на правильность.
     *
     * //FIXME НЕДОДЕЛАНО!!!
     */
    public Fin(String name, double mass, Material material,
            FinShape shape, double length)
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
