package components;

import utils.ComponentBase;
import utils.RequirementHandler;
import utils.enums.FinShape;

/**
 * Represents a stabilizing fin component attached to the tail of a rocket.
 * Fins provide aerodynamic stability during flight.
 *
 * <p>
 * A {@code Fin} is typically attached to the empennage
 * {@link components.inner.Trapezoidal} of a rocket to stabilize its trajectory.
 * </p>
 *
 * <p>
 * The shape and length of the fin are key characteristics defining its
 * aerodynamic properties.
 * </p>
 *
 * <p>
 * This class is authored by Gleb Kiva.
 * </p>
 *
 * @author Gleb Kiva
 * @see Component
 * @see components.inner.Trapezoidal
 */
public final class Fin extends Component {

    /**
     * The shape of the fin.
     */
    public final FinShape shape;

    /**
     * The length of the fin in meters.
     */
    public final double length;

    /**
     * Constructs a new {@code Fin} object with specified base information,
     * fin shape, and length.
     *
     * @param componentBase The base information of the fin component.
     * @param shape         The shape of the fin.
     * @param length        The length of the fin in meters.
     * @throws IllegalArgumentException If the length is not positive.
     */
    public Fin(final ComponentBase componentBase,
            final FinShape shape, final double length)
            throws IllegalArgumentException {
        super(componentBase);
        this.shape = shape;
        this.length = RequirementHandler.requirePositive(length);
    }

    /**
     * Returns a string representation of the fin.
     *
     * @return A string representation of the fin.
     */
    @Override
    public String toString() {
        return String.format("| | | |_External %s\n", name);
    }
}
