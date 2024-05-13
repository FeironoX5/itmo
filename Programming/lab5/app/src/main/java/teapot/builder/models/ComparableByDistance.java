package teapot.builder.models;

public interface ComparableByDistance extends Comparable<ComparableByDistance> {
    Long getDistance();

    @Override
    default int compareTo(ComparableByDistance o) {
        return (int) (getDistance() - o.getDistance());
    }
}
