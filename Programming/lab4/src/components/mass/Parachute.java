package components.mass;

import components.Rocket;
import utils.ComponentBase;
import utils.RequirementHandler;
import utils.enums.EventType;
import utils.enums.FlightPhases;
import utils.implementations.Event;
import utils.interfaces.EventListener;

public class Parachute extends MassComponent implements EventListener {
    public final double weightCapacity;

    public Parachute(final ComponentBase componentBase, final double weightCapacity)
            throws IllegalArgumentException {
        super(componentBase);
        this.weightCapacity = RequirementHandler.requirePositive(weightCapacity);
        Rocket.eventBus.addListener(EventType.FLIGHT_PHASE_ENTERED, this);
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
