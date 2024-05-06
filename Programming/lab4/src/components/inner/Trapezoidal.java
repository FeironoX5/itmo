package components.inner;

import java.util.LinkedList;

import components.Fin;
import utils.ComponentBase;
import utils.RequirementHandler;
import utils.exceptions.EmptyArrayException;
import utils.exceptions.EmptyStringException;
import utils.exceptions.NumberException;

public class Trapezoidal extends InnerComponent {
    private final LinkedList<Fin> fins;
    public final double sweep_angle;

    public Trapezoidal(final ComponentBase componentBase,
            final LinkedList<Fin> fins, final double sweepAngle)
            throws EmptyStringException, NumberException, EmptyArrayException {
        super(componentBase);
        this.fins = RequirementHandler.requireNonEmptyArray(fins);
        if (sweepAngle <= 0 || sweepAngle >= 90) {
            throw new NumberException("Неправильная угол наклона плавников");
        }
        sweep_angle = sweepAngle;
    }

    public LinkedList<Fin> getFins() {
        return fins;
    }

    @Override
    public String toString() {
        String res = "";
        res += String.format("| | |_Внутренний %s\n", name);
        for (Fin c : getFins()) {
            res += c.toString();
        }
        return res;
    }

}
