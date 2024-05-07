package components;

import java.util.Objects;

/**
 * The {@code Vector} class is used to store a vector in 3D space.
 * It represents a point in the Cartesian coordinate system.
 *
 * @author Gleb Kiva
 */
public final class Vector {
    /**
     * The x-coordinate of the vector.
     */
    public double x;
    /**
     * The y-coordinate of the vector.
     */
    public double y;
    /**
     * The z-coordinate of the vector.
     */
    public double z;

    /**
     * Constructs a new vector with the specified coordinates.
     *
     * @param x The x-coordinate of the vector.
     * @param y The y-coordinate of the vector.
     * @param z The z-coordinate of the vector.
     */
    public Vector(
            final double x,
            final double y,
            final double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Constructs a new vector that is a copy of the specified vector.
     *
     * @param vector The vector to be copied.
     */
    public Vector(final Vector vector) {
        this.x = vector.x;
        this.y = vector.y;
        this.z = vector.z;
    }

    /**
     * Sets the coordinates of this vector to be the same
     * as the specified vector.
     *
     * @param vector The vector whose coordinates are to be copied.
     */
    public void set(final Vector vector) {
        this.x = vector.x;
        this.y = vector.y;
        this.z = vector.z;
    }

    /**
     * Adds two vectors and returns the result as a new vector.
     *
     * @param a The first vector.
     * @param b The second vector.
     * @return The sum of the two vectors.
     */
    public static Vector add(final Vector a, final Vector b) {
        return new Vector(a.x + b.x, a.y + b.y, a.z + b.z);
    }

    /**
     * Multiplies a vector by a scalar value and returns
     * the result as a new vector.
     *
     * @param original The original vector.
     * @param k        The scalar value.
     * @return The result of multiplying the original vector
     *         by the scalar value.
     */
    public static Vector multiply(final Vector original, final double k) {
        return new Vector(original.x * k, original.y * k, original.z * k);
    }

    /**
     * Rotates a vector around the z-axis by the specified angle (in degrees)
     * and returns the result as a new vector.
     *
     * @param original The original vector.
     * @param delta    The angle of rotation in degrees.
     * @return The result of rotating the original vector.
     */
    public static Vector rotateZ(final Vector original, final double delta) {
        double x = original.x;
        double y = original.y;
        double deltaRad = -(Math.PI * delta / 180);
        return new Vector(
                x * Math.cos(deltaRad) - y * Math.sin(deltaRad),
                x * Math.sin(deltaRad) + y * Math.cos(deltaRad),
                original.z);
    }

    /**
     * Returns a hash code value for the vector.
     *
     * @return A hash code value for this vector.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.toString());
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param object The reference object with which to compare.
     * @return
     *         {@code true} if objects has the same x, y, z;
     *         {@code false} otherwise.
     */
    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Vector other = (Vector) object;
        return Objects.equals(this.x, other.x)
                && Objects.equals(this.y, other.y)
                && Objects.equals(this.z, other.z);
    }

    /**
     * Returns a string representation of the vector.
     *
     * @return A string representation of the vector.
     */
    @Override
    public String toString() {
        return String.format("(%f, %f, %f)", x, y, z);
    }
}
