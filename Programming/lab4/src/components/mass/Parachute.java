package components.mass;

import components.Rocket;
import utils.enums.EventType;
import utils.enums.FlightPhases;
import utils.enums.Material;
import utils.exceptions.NaturalNumberException;
import utils.exceptions.NumberException;
import utils.implementations.Event;
import utils.interfaces.EventListener;
import utils.exceptions.NameException;

public class Parachute extends MassComponent implements EventListener {
    private final double diameter;
    private final double weightCapacity;

    public Parachute(String name, double mass, Material material,
            double diameter, double weightCapacity)
            throws NameException, NumberException, NaturalNumberException {
        super(name, mass, material);
        if (diameter <= 0) {
            throw new NaturalNumberException("Неправильный диаметр компонента");
        }
        this.diameter = diameter;
        if (weightCapacity <= 0) {
            throw new NaturalNumberException("Неправильная грузоподъёмность компонента");
        }
        this.weightCapacity = weightCapacity;
        Rocket.eventBus.addListener(EventType.FLIGHT_PHASE_ENTERED, this);
    }

    public double getDiameter() {
        return diameter;
    }

    public double getWeightCapacity() {
        return weightCapacity;
    }

    @Override
    public void handle(Event<?> event) {
        switch (event.getType()) {
            case FLIGHT_PHASE_ENTERED:
                if (event.getData() == FlightPhases.SLOW_DESCENT) {
                    System.out.println();
                }
                break;
            default:
                break;
        }
    }

}
