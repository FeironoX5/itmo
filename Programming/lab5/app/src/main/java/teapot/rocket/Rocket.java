package teapot.rocket;

import static java.lang.Double.max;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Objects;

import teapot.rocket.components.assembly.NoseCone;
import teapot.rocket.components.assembly.Stage;
import teapot.rocket.utils.RequirementHandler;
import teapot.rocket.utils.Vector;
import teapot.rocket.utils.enums.EventType;
import teapot.rocket.utils.exceptions.StageNotExistsException;
import teapot.rocket.utils.implementations.Event;
import teapot.rocket.utils.implementations.EventBus;
import teapot.rocket.utils.interfaces.EventListener;
import teapot.rocket.utils.interfaces.Physical;

/**
 * {@code Rocket} represents a rocket intended for a long journey.
 *
 * This class defines a rocket with its stages, nose cone, and provides methods
 * for calculating movement vector, separating stages,
 * and retrieving rocket properties.
 * It also handles events related to the rocket's status.
 *
 * @author Gleb Kiva
 */
public final class Rocket implements Physical {
    /**
     * The name of the rocket.
     */
    public final String name;
    /**
     * The manufacturer of the rocket.
     */
    public final String manufacturer;
    /**
     * The country of origin of the rocket.
     */
    public final String originCountry;
    /**
     * Stages, containing {@link org.teapot_rocket.components.body.BodyComponents}.
     */
    private final LinkedList<Stage> stages;
    /**
     * The nose cone of the rocket.
     */
    public final NoseCone cone;
    /**
     * The event bus for handling events related to the rocket.
     */
    public static EventBus eventBus = new EventBus();

    /**
     * Constructs a Rocket object with the specified name, manufacturer,
     * origin country, stages, and nose cone.
     *
     * @param name          the name of the rocket
     * @param manufacturer  the manufacturer of the rocket
     * @param originCountry the country of origin of the rocket
     * @param stages        the stages of the rocket
     * @param cone          the nose cone of the rocket
     * @throws IllegalArgumentException if any of the parameters
     *                                  are empty or null
     */
    public Rocket(final String name,
            final String manufacturer, final String originCountry,
            final LinkedList<Stage> stages, final NoseCone cone)
            throws IllegalArgumentException {
        this.name = RequirementHandler
                .requireNonEmptyString(name);
        this.manufacturer = RequirementHandler
                .requireNonEmptyString(manufacturer);
        this.originCountry = RequirementHandler
                .requireNonEmptyString(originCountry);
        this.stages = RequirementHandler
                .requireNonEmptyArray(stages);
        this.cone = Objects.requireNonNull(cone);
    }

    /**
     * Inner class to handle events related to the rocket's status.
     */
    public class RocketStatusHandler implements EventListener {
        /**
         * Initializes empty RocketStatusHandler.
         */
        public RocketStatusHandler() {
        }

        // TODO replace all prints with events
        // TODO add constructor and subscribe to events
        @Override
        public void handle(final Event<?> event) {

        }

    }

    /**
     * Calculates the movement vector of the rocket.
     *
     * @return the movement vector of the rocket
     */
    public Vector calculateMovement() {
        Vector vector = new Vector(0, 0, 0);
        return Vector.multiply(vector, -1);
    }

    /**
     * Separates the last stage of the rocket.
     *
     * @throws StageNotExistsException if no stages exist in the rocket
     */
    public void separateStage() throws StageNotExistsException {
        if (stages.size() == 0) {
            throw new StageNotExistsException();
        }
        this.stages.removeLast();
        System.out.printf("Stage %d successfully separated\n", stages.size() + 1);
        eventBus.notifyListeners(
                new Event<Integer>(
                        stages.size() + 1,
                        EventType.STAGE_SEPARATED));
    }

    /**
     * Retrieves the properties of the rocket.
     *
     * @return the properties of the rocket as a formatted string
     */
    public String getProperties() {
        return String.format(
                "Name: %s\nManufacturer: %s\nOrigin Country: %s\nNumber of Stages: %d\nWeight: %f\nWidth: %f\nHeight: %f",
                name, manufacturer, originCountry, getStages().size(), getWeight(), getWidth(), getHeight());
    }

    /**
     * Retrieves the stages of the rocket.
     *
     * @return the stages of the rocket
     */
    public LinkedList<Stage> getStages() {
        return stages;
    }

    @Override
    public double getWidth() {
        return max(cone.width,
                Collections.max(
                        stages,
                        Comparator.comparing(
                                stage -> stage.getWidth()))
                        .getWidth());
    }

    @Override
    public double getHeight() {
        return stages.stream()
                .map(Stage::getHeight)
                .mapToDouble(Double::doubleValue)
                .sum() + cone.getHeight();
    }

    @Override
    public double getWeight() {
        return stages.stream()
                .map(Stage::getWeight)
                .mapToDouble(Double::doubleValue)
                .sum() + cone.getWeight();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.toString());
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Rocket other = (Rocket) object;
        return Objects.equals(name, other.name)
                && Objects.equals(manufacturer, other.manufacturer)
                && Objects.equals(this.originCountry, other.originCountry);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder(String.format("%s\n", name));
        res.append(cone);
        for (int i = 0; i < stages.size(); i++) {
            res.append(stages.get(i));
        }
        res.append("|_|_|_________");
        return res.toString();
    }
}
