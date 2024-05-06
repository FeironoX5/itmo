package components;

import utils.ComponentBase;
import utils.RequirementHandler;
import utils.enums.Material;
import utils.exceptions.EmptyStringException;
import utils.exceptions.NonPositiveNumberException;
import utils.interfaces.Physical;

/**
 * {@code Component} используется для построения ракеты.
 * Он должен быть реализован как один из следующих классов:
 *
 * <pre>
 * Component
 *         | -BodyComponent
 *         | -AssemblyComponent
 *         | -InnerComponent
 *         | -OuterComponent // TODO добавить OuterComponent как отдельный класс
 * </pre>
 *
 * @author Gleb Kiva
 */
public abstract class Component implements Physical {
    /**
     * Название компонента, предполагает описание
     * области его применения, если возможно.
     */
    public final String name;
    public final double width;
    public final double height;
    public final double weight;
    /**
     * Материал компонента.
     *
     * @see Material
     */
    public final Material material;

    /**
     * Реализация конструктора {@code Component},
     * которая проверяет на правильность
     * поля {@code name}, и {@code mass}.
     *
     * @param name     Название компонента
     * @param mass     Вес компонента (кг)
     * @param material Материал компонента
     * @param type     Тип компонента
     * @throws EmptyStringException
     * @throws NonPositiveNumberException
     */
    public Component(ComponentBase componentBase)
            throws EmptyStringException, NonPositiveNumberException {
        this.name = RequirementHandler.requireNonEmptyString(componentBase.name);
        this.width = RequirementHandler.requirePositive(componentBase.width);
        this.height = RequirementHandler.requirePositive(componentBase.height);
        this.weight = RequirementHandler.requirePositive(componentBase.weight);
        this.material = componentBase.material;
        System.out.printf("Создан новый '%s'\n", this.name);
    }

    public Component(final Component base)
            throws EmptyStringException, NonPositiveNumberException {
        this.name = RequirementHandler.requireNonEmptyString(base.name);
        this.width = RequirementHandler.requirePositive(base.width);
        this.height = RequirementHandler.requirePositive(base.height);
        this.weight = RequirementHandler.requirePositive(base.weight);
        this.material = base.material;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public double getWeight() {
        return weight;
    }

}
