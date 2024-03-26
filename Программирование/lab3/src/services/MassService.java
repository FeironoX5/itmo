package services;

import components.*;
import components.assembly.Stage;
import components.body.BodyComponent;
import components.inner.InnerComponent;

public class MassService {
    public static double getMass(Rocket rocket) {
        double sum = 0;
        for (int i = 0; i < rocket.getActiveStages(); i++) {
            sum += getMass(rocket.getStage(i));
        }
        return sum + rocket.getCone().getMass();
    }

    public static double getMass(Stage component) {
        double sum = 0;
        for (BodyComponent c : component.getBody()) {
            sum += getMass(c);
        }
        return sum + component.getMass();
    }

    public static double getMass(BodyComponent component) {
        double sum = 0;
        for (InnerComponent c : component.getInners()) {
            sum += getMass(c);
        }
        return sum + component.getMass();
    }

    public static double getMass(Component component) {
        return component.getMass();
    }
}
