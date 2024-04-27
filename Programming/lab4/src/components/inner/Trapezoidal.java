package components.inner;

import components.Fin;
import utils.enums.Material;
import utils.exceptions.NameException;
import utils.exceptions.NaturalNumberException;
import utils.exceptions.NumberException;

public class Trapezoidal extends InnerComponent {
    private final Fin[] fins;
    private final double sweep_angle;

    public Trapezoidal(String name, double mass, Material material, 
            Fin[] fins, double sweepAngle)
            throws NameException, NumberException, NaturalNumberException {
        super(name, mass, material);
        if (fins.length == 0) {
            throw new NaturalNumberException("Неправильная длина компонента");
        }
        this.fins = fins;
        if (sweepAngle <= 0 || sweepAngle >= 90) {
            throw new NumberException("Неправильная угол наклона плавников");
        }
        sweep_angle = sweepAngle;
    }

    public Fin[] getFins() {
        return fins;
    }

    public double getSweep_angle() {
        return sweep_angle;
    }

    @Override
    public String toString() {
        String res = "";
        res += String.format("| | |_%s %s\n", getType(), getName());
        for (Fin c : getFins()) {
            res += c.toString();
        }
        return res;
    }

}
