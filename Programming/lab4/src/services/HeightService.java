package services;

import components.Rocket;
import components.assembly.Stage;
import components.body.BodyComponent;

/**
 * {@code HeightService} используется для
 * подсчёта высоты компонент разного уровня,
 * или ракеты в целом.
 *
 * @author Gleb Kiva
 */
public final class HeightService {
    private HeightService() {
    }

    /**
     * Возвращает высоту ракеты.
     *
     * @param rocket Ракета
     * @return Высота (м)
     */
    public static double getHeight(final Rocket rocket) {
        double sum = 0;
        for (int i = 0; i < rocket.getActiveStages(); i++) {
            sum += getHeight(rocket.getStage(i));
        }
        return sum + rocket.getCone().getHeight();
    }

    /**
     * Возвращает высоту ступени.
     *
     * @param component Ступень ракеты
     * @return Высота (м)
     */
    public static double getHeight(final Stage component) {
        double sum = 0;
        for (BodyComponent c : component.getBody()) {
            sum += getHeight(c);
        }
        return sum;
    }

    /**
     * Возвращает высоту основного компонента.
     *
     * @param component Основной компонент
     * @return Высота компонента (м)
     */
    public static double getHeight(final BodyComponent component) {
        return component.getHeight();
    }

}
