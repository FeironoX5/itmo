package utils.enums;

/**
 * Тип компонентов, из которых состоит ракета.
 *
 * @author Gleb Kiva
 */

public enum ComponentType { // TODO избавится от enum, заменить на Instance of
    /**
     * Сборочный компонент (напр. ).
     */
    ASSEMBLY("сборочный"),
    /**
     * Основной компонент.
     */
    BODY("основной"),
    /**
     * Внутренний компонент.
     */
    INNER("внутренний"),
    /**
     * Внешний компонент.
     */
    OUTER("внешний");

    /**
     * Название типа используется для
     * вывода сведений о компоненте в консоль.
     */
    private String name;

    ComponentType(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("%s компонент", name);
    }
}
