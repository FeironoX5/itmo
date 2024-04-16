package services;

import components.*;
import components.assembly.Stage;
import components.body.BodyComponent;

import static java.lang.Double.max;

public class HeightService {
    public static double getHeight(Rocket rocket) {
        double sum = 0;
        for (int i = 0; i < rocket.getActiveStages(); i++) {
            sum += getHeight(rocket.getStage(i));
        }
        return sum + rocket.getCone().getHeight();
    }

    public static double getHeight(Stage component) {
        double sum = 0;
        for (BodyComponent c : component.getBody()) {
            sum += getHeight(c);
        }
        return sum;
    }

    public static double getHeight(BodyComponent component) {
        return component.getHeight();
    }


}
