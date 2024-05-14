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
     * Represents the base information of a route including its name, coordinates,
     * from, to, and distance.
     */
    static public class Base implements Comparable<Base> {
        private String name;
        private Coordinates coordinates;
        private Location from;
        private Location to;
        private Long distance;

        /**
         * Constructs a Base with the specified details.
         *
         * @param name        The name of the base.
         * @param coordinates The coordinates of the base.
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

        public String getName() {
            return name;
        }

        public Coordinates getCoordinates() {
            return coordinates;
        }

        public Location getFrom() {
            return from;
        }

        public Location getTo() {
            return to;
        }

        public Long getDistance() {
            return distance;
        }

        @Override
        public int compareTo(Base o) {
            return -distance.compareTo(o.getDistance());
        }
    }

    public int getId() {
        return id;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public void setBase(Base base) {
        this.base = base;
    }

    public Base getBase() {
        return base;
    }

    @Override
    public String toString() {
        return String.format("Route #%s named %s with coordinates %s created at %s\nFrom: %s\nTo: %s\nDistance: %s",
                id, base.name, base.coordinates, creationDate, base.from, base.to, base.distance);
    }

    @Override
    public int compareTo(Route o) {
        return getBase().compareTo(o.getBase());
    }
}
