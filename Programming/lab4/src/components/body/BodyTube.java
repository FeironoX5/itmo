package components.body;

import utils.interfaces.EventListener;

import java.util.LinkedList;

import components.Rocket;
import components.Vector;
import components.inner.InnerComponent;
import utils.ComponentBase;
import utils.RequirementHandler;
import utils.enums.EventType;
import utils.exceptions.EmptyArrayException;
import utils.exceptions.EmptyStringException;
import utils.exceptions.NonPositiveNumberException;
import utils.implementations.Event;
import utils.interfaces.Rotatable;

public class BodyTube extends BodyComponent implements Rotatable {
    public final double wallThickness;
    private double rotation;
    IndoorTemperatureController indoorTemperatureController;

    public BodyTube(final ComponentBase componentBase,
            final LinkedList<InnerComponent> inners, final double wallThickness)
            throws EmptyStringException, NonPositiveNumberException, EmptyArrayException {
        super(componentBase, inners);
        this.wallThickness = RequirementHandler.requirePositive(wallThickness);
        this.rotation = 0;
        this.indoorTemperatureController = new IndoorTemperatureController();
    }

    // TODO Event bus implementation
    class IndoorTemperatureController implements EventListener {
        private float indoorTemprerature;

        IndoorTemperatureController() {
            Rocket.eventBus.addListener(EventType.STAGE_SEPARATED, this);
        }

        public float getIndoorTemprerature() {
            return indoorTemprerature;
        }

        @Override
        public void handle(Event<?> event) {
            // TODO Event bus implementation
            switch (event.getType()) {
                case STAGE_SEPARATED:
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public Vector calculateMovement() {
        Vector vector = super.calculateMovement();
        return Vector.rotateZ(vector, rotation);
    }

    @Override
    public void rotate(double delta) {
        rotation = (rotation + delta + 180) % 360 - 180;
        System.out.printf("Поворот %s успешно назначен на %f градусов\n", name, rotation);
    }

    @Override
    public double getRotation() {
        return rotation;
    }

}
