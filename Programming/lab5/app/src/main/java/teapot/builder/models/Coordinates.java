package teapot.builder.models;

import teapot.builder.utils.RequirementHandler;

public class Coordinates {
    private final int x;
    private final Double y;

    public Coordinates(final int x, final Double y) {
        this.x = RequirementHandler.requireLessThan(x, 751);
        this.y = RequirementHandler.requireLessThan(y, 238.0);
    }

    public int getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    @Override
    public String toString() {
        return String.format("{%s, %s}", x, y);
    }
}
