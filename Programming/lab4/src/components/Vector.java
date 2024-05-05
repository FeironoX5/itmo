package components;

import java.util.Objects;

/**
 * {@code Vector} это вектор который.
 *
 * @author Gleb Kiva
 */
public class Vector {
    public double x;
    public double y;
    public double z;

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector(Vector vector) {
        this.x = vector.x;
        this.y = vector.y;
        this.z = vector.z;
    }

    public void set(Vector vector) {
        this.x = vector.x;
        this.y = vector.y;
        this.z = vector.z;
    }

    public static Vector add(Vector a, Vector b) {
        return new Vector(a.x + b.x, a.y + b.y, a.z + b.z);
    }

    public static Vector multiply(Vector original, double k) {
        return new Vector(original.x * k, original.y * k, original.z * k);
    }

    public static Vector rotateZ(Vector original, double delta) {
        double x = original.x;
        double y = original.y;
        double deltaRad = -(Math.PI * delta / 180);
        return new Vector(
                x * Math.cos(deltaRad) - y * Math.sin(deltaRad),
                x * Math.sin(deltaRad) + y * Math.cos(deltaRad),
                original.z
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.toString());
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Vector other = (Vector) object;
        return Objects.equals(this.x, other.x) && Objects.equals(this.y, other.y) && Objects.equals(this.z, other.z);
    }

    @Override
    public String toString() {
        return String.format("(%f, %f, %f)", x, y, z);
    }

}
