package components.assembly;

import enums.Material;
import enums.NoseConeShape;
import exceptions.NaturalNumberException;
import exceptions.NumberException;
import exceptions.NameException;

public class NoseCone extends AssemblyComponent {
    private final double diameter;
    private final double height;
    private final double wallThickness;
    private final NoseConeShape shape;

    public NoseCone(String name, double mass, Material material, double diameter, double height, double wallThickness,
            NoseConeShape shape) throws NameException, NumberException {
        super(name, mass, material);
        if (diameter <= 0) {
            throw new NaturalNumberException("Неправильный диаметр компонента");
        }
        this.diameter = diameter;
        if (height <= 0) {
            throw new NaturalNumberException("Неправильная высота компонента");
        }
        this.height = height;
        if (wallThickness <= 0) {
            throw new NaturalNumberException("Неправильная толщина стенок компонента");
        }
        this.wallThickness = wallThickness;
        this.shape = shape;
    }

    public double getDiameter() {
        return diameter;
    }

    public double getHeight() {
        return height;
    }

    public double getWallThickness() {
        return wallThickness;
    }

    public NoseConeShape getShape() {
        return shape;
    }
}
