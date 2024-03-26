package services;

import components.*;
import components.assembly.AssemblyComponent;
import components.assembly.Stage;
import components.body.BodyComponent;

import java.util.Arrays;

import static java.lang.Double.max;

public class DiameterService {
    public static double getDiameter(Rocket rocket) {
        double maxd = 0;
//        Arrays.stream(rocket.getStages()).max() // TODO
        for (int i = 0; i < rocket.getActiveStages(); i++) {
            maxd = max(maxd, getDiameter(rocket.getStage(i)));
        }
        return max(maxd, rocket.getCone().getDiameter());
    }

    public static double getDiameter(Stage component) {
        double maxd = 0;
        for (BodyComponent c : component.getBody()) {
            maxd = max(maxd, getDiameter(c));
        }
        return maxd;
    }

    public static double getDiameter(BodyComponent component) {
        return component.getDiameter();
    }

}
