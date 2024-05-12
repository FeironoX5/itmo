package teapot.builder;

import java.util.Objects;

import teapot.builder.utils.RequirementHandler;

public final class Location {
    private final long x;
    private final Float y;
    private final String name;

    public Location(final long x, final Float y, final String name) {
        this.x = Objects.requireNonNull(x);
        this.y = Objects.requireNonNull(y);
        this.name = RequirementHandler.requireNonEmptyString(name);
    }

    public long getX() {
        return x;
    }

    public Float getY() {
        return y;
    }

    public String getName() {
        return name;
    }
}
