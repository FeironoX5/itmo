package teapot.builder.models;

import java.time.ZonedDateTime;
import java.util.Objects;

import teapot.builder.utils.RequirementHandler;

public class Route implements Comparable<Route> {
    private static int LAST_USED_ID = 1;
    private final int id;
    private final ZonedDateTime creationDate;
    private Base base;

    public Route(final int id,
            final ZonedDateTime creationDate,
            final Base base) {
        this.id = id;
        this.creationDate = creationDate;
        this.base = base;
    }

    public Route(final Base base) {
        this(LAST_USED_ID++, ZonedDateTime.now(), base);
    }

    static public class Base implements Comparable<Base> {
        private String name;
        private Coordinates coordinates;
        private Location from;
        private Location to;
        private Long distance;

        public Base(final String name,
                final Coordinates coordinates,
                final Location from, final Location to,
                final Long distance) {
            this.name = RequirementHandler.requireNonEmptyString(name);
            this.coordinates = Objects.requireNonNull(coordinates);
            this.from = Objects.requireNonNull(from);
            this.to = Objects.requireNonNull(to);
            this.distance = RequirementHandler.requireGreaterThan(distance, 1l);
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
