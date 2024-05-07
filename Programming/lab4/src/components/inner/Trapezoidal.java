package components.inner;

import components.Fin;
import utils.ComponentBase;
import utils.RequirementHandler;
import utils.exceptions.NumberException;

import java.util.LinkedList;

/**
 * Represents a trapezoidal inner component used in rocket construction.
 *
 * <p>
 * This class extends {@link InnerComponent} and represents a trapezoidal-shaped
 * component
 * placed inside the rocket structure, typically used for aerodynamic purposes.
 * </p>
 *
 * <p>
 * The trapezoidal inner component consists of a set of fins with a specified
 * sweep angle.
 * It provides methods to retrieve the list of fins and validate the sweep
 * angle.
 * </p>
 *
 * <p>
 * To create a trapezoidal inner component, specify its base information, list
 * of fins,
 * and the sweep angle (angle of inclination of the fins).
 * The sweep angle must be within the range (0, 90) degrees.
 * </p>
 *
 * <p>
 * This class is designed to be used as an aerodynamic component within rocket
 * assemblies,
 * contributing to stability and control during flight.
 * </p>
 *
 * <p>
 * The {@link #toString()} method generates a string representation of the
 * trapezoidal component,
 * listing its internal fins.
 * </p>
 *
 * <p>
 * When creating instances of {@code Trapezoidal}, ensure that the sweep angle
 * is valid,
 * and the list of fins is not empty.
 * </p>
 *
 * @author Gleb Kiva
 * @see InnerComponent
 * @see Fin
 */
public class Trapezoidal extends InnerComponent {
    /**
     * The list of fins attached to the trapezoidal component.
     */
    private final LinkedList<Fin> fins;

    /**
     * The sweep angle (angle of inclination) of the fins in degrees.
     */
    public final double sweep_angle;

    /**
     * Constructs a trapezoidal inner component with the specified base information,
     * list of fins, and sweep angle.
     *
     * @param componentBase The base information of the trapezoidal component.
     * @param fins          The list of fins attached to the trapezoidal component.
     * @param sweepAngle    The sweep angle (angle of inclination) of the fins (in
     *                      degrees).
     * @throws IllegalArgumentException If the list of fins is empty or the sweep
     *                                  angle is invalid.
     */
    public Trapezoidal(final ComponentBase componentBase,
            final LinkedList<Fin> fins, final double sweepAngle)
            throws IllegalArgumentException {
        super(componentBase);
        this.fins = RequirementHandler.requireNonEmptyArray(fins);
        if (sweepAngle <= 0 || sweepAngle >= 90) {
            throw new NumberException("Invalid sweep angle for fins");
        }
        this.sweep_angle = sweepAngle;
    }

    /**
     * Retrieves the list of fins attached to the trapezoidal component.
     *
     * @return The list of fins attached to the trapezoidal component.
     */
    public LinkedList<Fin> getFins() {
        return fins;
    }

    /**
     * Generates a string representation of the trapezoidal component,
     * including its internal fins.
     *
     * @return A string representation of the trapezoidal component.
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder(
                String.format("| | |_Внутренний %s\n", name));
        for (Fin c : getFins()) {
            res.append(c);
        }
        return res.toString();
    }
}
