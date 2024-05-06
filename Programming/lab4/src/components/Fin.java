package components;

import utils.ComponentBase;
import utils.RequirementHandler;
import utils.enums.FinShape;
import utils.exceptions.EmptyStringException;
import utils.exceptions.NonPositiveNumberException;

/**
 * {@code Fin} реализует {@link Component},
 * представляя собой стабилизатор на хвосте ракеты,
 * который крепится к оперению {@link components.inner.Trapezoidal}.
 *
 * @author Gleb Kiva
 */
public final class Fin extends Component {
    /**
     * Форма стабилизатора.
     */
    public final FinShape shape;
    /**
     * Длина стабилизатора.
     */
    public final double length;

    /**
     * Создаёт {@code Fin},
     * которая проверяет введенные данные
     * на правильность.
     *
     * @param name     Название стабилизатора
     * @param mass     Вес стабилизатора (кг)
     * @param material Материал стабилизатора
     * @param shape    Форма стабилизатора
     * @param length   Длина стабилизатора
     */
    public Fin(final ComponentBase componentBase, final FinShape shape, final double length)
            throws NonPositiveNumberException, EmptyStringException {
        super(componentBase);
        this.shape = shape;
        this.length = RequirementHandler.requirePositive(length);
    }

    @Override
    public String toString() {
        String res = "";
        res += String.format("| | | |_Внешний %s\n", name);
        return res;
    }

}
