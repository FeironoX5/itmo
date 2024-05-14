package teapot.builder.models;

import java.util.Objects;

import teapot.builder.utils.RequirementHandler;

/**
 * Represents a location with coordinates and a name.
 *
 * @author Gleb Kiva
 */
public final class Location {
    private final long x;
    private final Float y;
    private final String name;

    /**
     * Constructs a Location with the specified coordinates and name.
     *
     * @param x     The x-coordinate of the location.
     * @param y     The y-coordinate of the location.
     * @param name  The name of the location.
     */
    public Location(final long x, final Float y, final String name) {
        this.x = x;
        this.y = Objects.requireNonNull(y);
        this.name = RequirementHandler.requireNonEmptyString(name);
    }

    /**
     * Returns the x-coordinate of the location.
     *
     * @return The x-coordinate.
     */
    public long getX() {
        return x;
    }

    /**
     * Returns the y-coordinate of the location.
     *
     * @return The y-coordinate.
     */
    public Float getY() {
        return y;
    }

    /**
     * Returns the name of the location.
     *
     * @return The name of the location.
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("%s {%s, %s}", name, x, y);
    }
}
