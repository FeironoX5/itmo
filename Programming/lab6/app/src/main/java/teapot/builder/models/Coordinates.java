package teapot.builder.models;

import teapot.builder.utils.RequirementHandler;

/**
 * Represents coordinates with x and y values.
 *
 * @author Gleb Kiva
 */
public class Coordinates {
    private final int x;
    private final Double y;

    /**
     * Constructs coordinates with the specified x and y values.
     *
     * @param x The x-coordinate value (must be less than 751).
     * @param y The y-coordinate value (must be less than 238.0).
     */
    public Coordinates(final int x, final Double y) {
        this.x = RequirementHandler.requireLessThan(x, 751);
        this.y = RequirementHandler.requireLessThan(y, 238.0);
    }

    /**
     * Returns the x-coordinate value.
     *
     * @return The x-coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y-coordinate value.
     *
     * @return The y-coordinate.
     */
    public Double getY() {
        return y;
    }

    @Override
    public String toString() {
        return String.format("{%s, %s}", x, y);
    }
}
