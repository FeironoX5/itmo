package components;

import java.util.Objects;

import utils.ComponentBase;
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
    public Component(final ComponentBase componentBase)
            throws IllegalArgumentException {
        Objects.requireNonNull(componentBase);
        this.name = componentBase.name;
        this.width = componentBase.width;
        this.height = componentBase.height;
        this.weight = componentBase.weight;
        this.material = componentBase.material;
        System.out.printf("Создан новый '%s'\n", this.name);
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
