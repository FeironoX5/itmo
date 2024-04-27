package services;

import components.Component;
import components.Rocket;
import components.assembly.Stage;
import components.body.BodyComponent;
import components.inner.InnerComponent;

/**
 * {@code MassService} используется для
 * подсчёта общей массы компонент разного уровня,
 * или ракеты в целом.
 *
 * @author Gleb Kiva
 */
public final class MassService {
    private MassService() {
    }

    /**
     * Возвращает общую массу ракеты.
     *
     * @param rocket Ракета
     * @return Общая масса ракеты (кг)
     */
    public static double getMass(final Rocket rocket) {
        double sum = 0;
        for (int i = 0; i < rocket.getActiveStages(); i++) {
            sum += getMass(rocket.getStage(i));
        }
        return sum + rocket.getCone().getMass();
    }

    /**
     * Возвращает общую массу ступени.
     *
     * @param component Ступень ракеты
     * @return Общая масса ступени (кг)
     */
    public static double getMass(final Stage component) {
        double sum = 0;
        for (BodyComponent c : component.getBody()) {
            sum += getMass(c);
        }
        return sum + component.getMass();
    }

    /**
     * Возвращает общую массу основного компонента.
     *
     * @param component Основной компонент
     * @return Общая масса основного компонента (кг)
     */
    public static double getMass(final BodyComponent component) {
        double sum = 0;
        for (InnerComponent c : component.getInners()) {
            sum += getMass(c);
        }
        return sum + component.getMass();
    }

    /**
     * Возвращает массу компонента.
     *
     * @param component Компонент
     * @return Масса компонента (кг)
     */
    public static double getMass(final Component component) {
        return component.getMass();
    }
}
