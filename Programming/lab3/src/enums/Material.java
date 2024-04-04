package enums;

public enum Material {
    SILK(1320), CELLOPHANE(1420), ALUMINIUM(2700), STEEL(8050), FUEL(1950);

    private double density; // kg/m3

    Material(double density) {
        this.density = density;
    }

    public double getDensity() {
        return this.density;
    }

    @Override
    public String toString() {
        return density + "кг/(м^3)";
    }
}
