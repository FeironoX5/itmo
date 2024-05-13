package teapot.builder.models;

import java.time.ZonedDateTime;
import java.util.Objects;

import teapot.builder.utils.RequirementHandler;

public class Route implements Comparable<Route> {
    private static int LAST_USED_ID = 1;
    private final int id;
    private String name;
    private Coordinates coordinates;
    private final ZonedDateTime creationDate;
    private Location from;
    private Location to;
    private Long distance;

    public Route(final String name,
            final Coordinates coordinates,
            final Location from, final Location to,
            final Long distance) {
        this.id = LAST_USED_ID++;
        this.name = RequirementHandler.requireNonEmptyString(name);
        this.coordinates = Objects.requireNonNull(coordinates);
        this.creationDate = ZonedDateTime.now();
        this.from = Objects.requireNonNull(from);
        this.to = Objects.requireNonNull(to);
        this.distance = RequirementHandler.requireGreaterThan(distance, 1l);
    }

    public void setData(Route otherRoute) {
        this.name = otherRoute.name;
        this.coordinates = otherRoute.coordinates;
        this.from = otherRoute.from;
        this.to = otherRoute.to;
        this.distance = otherRoute.distance;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
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
    public String toString() {
        return String.format("Route #%s named %s with coordinates %s created at %s\nFrom: %s\nTo: %s\nDistance: %s",
                id, name, coordinates, creationDate, from, to, distance);
    }

    @Override
    public int compareTo(Route o) {
        return (int) (distance - o.getDistance());
    }

}
