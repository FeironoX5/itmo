package teapot.builder.models;

import java.time.ZonedDateTime;
import java.util.Objects;

import teapot.builder.utils.RequirementHandler;

/**
 * Represents a route with specific details including its identifier, creation
 * date, and base information.
 *
 * @author Gleb Kiva
 */
public class Route implements Comparable<Route> {
    private static int LAST_USED_ID = 1;
    private final int id;
    private final ZonedDateTime creationDate;
    private Base base;

    /**
     * Constructs a Route with the specified ID, creation date, and base
     * information.
     *
     * @param id           The identifier of the route.
     * @param creationDate The date and time when the route was created.
     * @param base         The base information (name, coordinates, from, to,
     *                     distance) for this route.
     */
    public Route(final int id, final ZonedDateTime creationDate, final Base base) {
        this.id = id;
        this.creationDate = creationDate;
        this.base = base;
    }

    /**
     * Constructs a Route with a generated ID and the current system time as the
     * creation date.
     *
     * @param base The base information (name, coordinates, from, to, distance) for
     *             this route.
     */
    public Route(final Base base) {
        this(LAST_USED_ID++, ZonedDateTime.now(), base);
    }

    /**
     * Represents the information of a route including its name, coordinates,
     * from, to, and distance.
     */
    static public class Base implements Comparable<Base> {
        private String name;
        private Coordinates coordinates;
        private Location from;
        private Location to;
        private Long distance;

        /**
         * Constructs a route base with the specified details.
         *
         * @param name        The name of the route.
         * @param coordinates The coordinates of the route.
         * @param from        The starting location of the route.
         * @param to          The destination location of the route.
         * @param distance    The distance of the route.
         */
        public Base(final String name, final Coordinates coordinates,
                final Location from, final Location to, final Long distance) {
            this.name = RequirementHandler.requireNonEmptyString(name);
            this.coordinates = Objects.requireNonNull(coordinates);
            this.from = Objects.requireNonNull(from);
            this.to = Objects.requireNonNull(to);
            this.distance = RequirementHandler.requireGreaterThan(distance, 1L);
        }

        /**
         * Retrieves the name of the route.
         * 
         * @return The name of the route.
         */
        public String getName() {
            return name;
        }

        /**
         * Retrieves the coordinates of the route.
         * 
         * @return The coordinates of the route.
         */
        public Coordinates getCoordinates() {
            return coordinates;
        }

        /**
         * Retrieves the starting location of the route.
         * 
         * @return The starting location of the route.
         */
        public Location getFrom() {
            return from;
        }

        /**
         * Retrieves the destination location of the route.
         * 
         * @return The destination location of the route.
         */
        public Location getTo() {
            return to;
        }

        /**
         * Retrieves the distance of the route.
         * 
         * @return The distance of the route.
         */
        public Long getDistance() {
            return distance;
        }

        /**
         * Compares this route base with another route base based on their distances.
         * 
         * @param o The route base to compare with.
         * @return A negative integer, zero, or a positive integer as this route base
         *         has a distance less than, equal to, or greater than the specified
         *         base.
         */
        @Override
        public int compareTo(Base o) {
            return -distance.compareTo(o.getDistance());
        }
    }

    /**
     * Retrieves the identifier of the route.
     * 
     * @return The identifier of the route.
     */
    public int getId() {
        return id;
    }

    /**
     * Retrieves the creation date of the route.
     * 
     * @return The creation date of the route.
     */
    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Updates the base information of the route.
     * 
     * @param base The new base information to set.
     */
    public void setBase(Base base) {
        this.base = base;
    }

    /**
     * Retrieves the base information of the route.
     * 
     * @return The base information of the route.
     */
    public Base getBase() {
        return base;
    }

    /**
     * Provides a string representation of the route.
     * 
     * @return A string representation of the route.
     */
    @Override
    public String toString() {
        return String.format("Route #%s named %s with coordinates %s created at %s\nFrom: %s\nTo: %s\nDistance: %s",
                id, base.name, base.coordinates, creationDate, base.from, base.to, base.distance);
    }

    /**
     * Compares this route with another route based on their base information.
     * 
     * @param o The route to compare with.
     * @return A negative integer, zero, or a positive integer as this route is
     *         less than, equal to, or greater than the specified route.
     */
    @Override
    public int compareTo(Route o) {
        return getBase().compareTo(o.getBase());
    }
}
