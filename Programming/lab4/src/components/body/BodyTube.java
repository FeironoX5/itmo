package components.body;

import utils.interfaces.EventListener;

import components.Rocket;
import components.Vector;
import components.inner.InnerComponent;
import utils.enums.EventType;
import utils.enums.Material;
import utils.exceptions.NameException;
import utils.exceptions.NaturalNumberException;
import utils.exceptions.NumberException;
import utils.implementations.Event;
import utils.interfaces.Rotatable;

public class BodyTube extends BodyComponent implements Rotatable {
    private final double wallThickness;
    private double rotation;
    IndoorTemperatureController indoorTemperatureController;

    public BodyTube(String name, double mass, Material material,
            InnerComponent[] inners, double height, double diameter, double wallThickness)
            throws NameException, NumberException, NaturalNumberException {
        super(name, mass, material, inners, height, diameter);
        if (wallThickness <= 0) {
            throw new NaturalNumberException("Неправильная толщина стенок компонента");
        }
        this.wallThickness = wallThickness;
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
        System.out.printf("Поворот %s успешно назначен на %f градусов\n", getName(), rotation);
    }

    @Override
    public double getRotation() {
        return rotation;
    }

    public double getWallThickness() {
        return wallThickness;
    }
}
