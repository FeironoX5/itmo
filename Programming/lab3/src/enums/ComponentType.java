package enums;

public enum ComponentType {
    ASSEMBLY("сборочный"), BODY("основной"), INNER("внутренний"), OUTER("внешний");
    private String name; // kg/m3

    ComponentType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("%s компонент", name);
    }
}
