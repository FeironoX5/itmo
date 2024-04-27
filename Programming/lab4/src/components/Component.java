package components;

import utils.enums.ComponentType;
import utils.enums.Material;
import utils.exceptions.NameException;
import utils.exceptions.NaturalNumberException;
import utils.exceptions.NumberException;

/**
 * {@code Component} испоется для построения ракеты.
 * Он должен быть реализован как один из следуюих классов:
 *
 * <pre>
 * Component
 *         | -BodyComponent
 *         | -AssemlyComponent
 *         | -InnerComponent
 *         | -OuterComponent // TODO добавить OuterComponent как отдельный класс
 * </pre>
 *
 * @author Gleb Kiva
 */
public abstract class Component {
    /**
     * Название компонента, предполагает описание
     * области его применения, если возможно.
     */
    private final String name;
    /**
     * Вес компонента (кг).
     */
    private final double mass;
    /**
     * Тип компонента.
     *
     * @see ComponentType
     */
    private final ComponentType type;
    /**
     * Материал компонента.
     *
     * @see Material
     */
    private final Material material;

    /**
     * Реализация конструктора {@code Component},
     * которая проверяет на правильность
     * поля {@code name}, и {@code mass}.
     *
     * @param name     Название компонента
     * @param mass     Вес компонента (кг)
     * @param material Материал компонента
     * @param type     Тип компонента
     * @throws NameException
     * @throws NumberException
     * @throws NaturalNumberException
     */
    public Component(final String name, final double mass,
            final Material material, final ComponentType type)
            throws NameException, NumberException, NaturalNumberException {
        if (name.isEmpty()) {
            throw new NameException("Неправильное имя компонента");
        }
        this.name = name;
        if (mass <= 0) {
            // TODO масса -> вес
            throw new NaturalNumberException("Неправильная масса компонента");
        }
        this.mass = mass;
        this.material = material;
        this.type = type;
        System.out.printf("Создан новый %s '%s'\n", this.type, this.name);
    }

    /**
     * Возвращает название компонента.
     *
     * @return Название компонента
     */
    public String getName() {
        return name;
    }

    /**
     * Возвращает вес компонента.
     *
     * @return Вес компонента
     */
    public double getMass() {
        return mass;
    }

    /**
     * Возвращает тип компонента.
     *
     * @return Тип компонента
     */
    public ComponentType getType() {
        return this.type;
    }

    /**
     * Возвращает материал компонента.
     *
     * @return Материал компонента
     */
    public Material getMaterial() {
        return material;
    }

}
