package utils.enums;

/**
 * Материал, из которого состоит {@link Component}.
 *
 * @author Gleb Kiva
 */
public enum Material {
    /**
     * Шёлк.
     */
    SILK(1320),
    /**
     * Целлофан.
     */
    CELLOPHANE(1420),
    /**
     * Алюминий.
     */
    ALUMINIUM(2700),
    /**
     * Сталь.
     */
    STEEL(8050),
    /**
     * Топливо.
     */
    FUEL(1950);

    /**
     * Плотность (кг/(м^3)).
     */
    private double density;

    Material(final double density) {
        this.density = density;
    }

    /**
     * Возвращает плотность материала.
     *
     * @return Плотность (кг/(м^3)).
     */
    public double getDensity() {
        return this.density;
    }

    @Override
    public String toString() {
        return density + "кг/(м^3)";
    }
}
