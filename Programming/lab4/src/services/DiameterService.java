package services;

import components.Rocket;
import components.assembly.Stage;
import components.body.BodyComponent;

import static java.lang.Double.max;

/**
 * {@code DiameterService} используется для
 * подсчёта диаметра компонент разного уровня,
 * или ракеты в целом.
 *
 * @author Gleb Kiva
 */
public final class DiameterService {
    private DiameterService() {
        // TODO уточнить не должен ли быть класс сервиса abstract
    }

    /**
     * Возвращает диаметр ракеты.
     *
     * @param rocket Ракета
     * @return Диаметр (м)
     */
    public static double getDiameter(final Rocket rocket) {
        double maxd = 0;
        // TODO Change to Arrays.stream(rocket.getStages()).max()
        for (int i = 0; i < rocket.getActiveStages(); i++) {
            maxd = max(maxd, getDiameter(rocket.getStage(i)));
        }
        return max(maxd, rocket.getCone().getDiameter());
    }

    /**
     * Возвращает диаметр ступени.
     *
     * @param component Ступень ракеты
     * @return Диаметр (м)
     */
    public static double getDiameter(final Stage component) {
        double maxd = 0;
        for (BodyComponent c : component.getBody()) {
            maxd = max(maxd, getDiameter(c));
        }
        return maxd;
    }

    /**
     * Возвращает диаметр компонента.
     *
     * @param component Компонент
     * @return Диаметр (м)
     */
    public static double getDiameter(final BodyComponent component) {
        return component.getDiameter();
    }

}
