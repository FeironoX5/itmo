package teapot.builder;

import java.time.ZonedDateTime;
import java.util.Objects;

import teapot.builder.utils.RequirementHandler;

public class Route implements Comparable {
    private final int id;
    private final String name;
    private final Coordinates coordinates;
    private final java.time.ZonedDateTime creationDate;
    private final Location from;
    private final Location to;
    private final Long distance;

    public Route(final int id,
            final String name, final Coordinates coordinates,
            final java.time.ZonedDateTime creationDate,
            final Location from, final Location to,
            final Long distance) {
        this.id = RequirementHandler.requirePositive(id);
        this.name = RequirementHandler.requireNonEmptyString(name);
        this.coordinates = Objects.requireNonNull(coordinates);
        this.creationDate = Objects.requireNonNull(creationDate);
        this.from = Objects.requireNonNull(from);
        this.to = Objects.requireNonNull(to);
        this.distance = RequirementHandler.requireGreaterThan(distance, 1l);
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
    public int compareTo(Object o) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
    }

}
