package teapot_rocket.utils.enums;

/**
 * Represents materials that can be used in rocket components.
 *
 * <p>
 * This enum defines various materials such as silk, cellophane, aluminum,
 * steel, and fuel, each with its associated density (in kg/m^3).
 * </p>
 *
 * <p>
 * The density of each material can be retrieved using the {@link #getDensity()}
 * method.
 * </p>
 *
 * @author Gleb Kiva
 */
public enum Material {

    /**
     * Silk material.
     */
    SILK(1320),

    /**
     * Cellophane material.
     */
    CELLOPHANE(1420),

    /**
     * Aluminum material.
     */
    ALUMINIUM(2700),

    /**
     * Steel material.
     */
    STEEL(8050),

    /**
     * Fuel material.
     */
    FUEL(1950);

    /**
     * The density of the material (in kg/m^3).
     */
    private final double density;

    /**
     * Constructs a Material with the specified density.
     *
     * @param density The density of the material (in kg/m^3).
     */
    Material(final double density) {
        this.density = density;
    }

    /**
     * Returns the density of the material.
     *
     * @return The density (in kg/m^3) of the material.
     */
    public double getDensity() {
        return this.density;
    }

    /**
     * Returns a string representation of the material including its density.
     *
     * @return A string representation including the density.
     */
    @Override
    public String toString() {
        return density + " kg/m^3";
    }
}
