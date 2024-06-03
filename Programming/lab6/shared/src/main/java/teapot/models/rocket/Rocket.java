package teapot.models.rocket;

import teapot.models.rocket.utils.ComponentBase;
import teapot.models.rocket.utils.Vector;
import teapot.models.rocket.utils.implementations.Event;
import teapot.models.rocket.utils.implementations.EventBus;
import teapot.models.rocket.utils.interfaces.EventListener;
import teapot.models.rocket.utils.interfaces.Physical;
import teapot.utils.RequirementHandler;
import teapot.models.rocket.components.assembly.NoseCone;
import teapot.models.rocket.components.assembly.Stage;
import teapot.models.rocket.components.body.BodyComponent;
import teapot.models.rocket.utils.enums.EventType;
import teapot.models.rocket.utils.enums.Material;
import teapot.models.rocket.utils.enums.NoseConeShape;
import teapot.models.rocket.utils.exceptions.StageNotExistsException;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Objects;

import static java.lang.Double.max;

/**
 * {@code Rocket} represents a rocket intended for a long journey.
 * <p>
 * This class defines a rocket with its stages, nose cone, and provides methods
 * for calculating movement vector, separating stages,
 * and retrieving rocket properties.
 * It also handles events related to the rocket's status.
 *
 * @author Gleb Kiva
 */
public final class Rocket implements Physical, Comparable<Rocket> {
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
     * Stages, containing {@link BodyComponent}.
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
     * Constructs a Rocket object with the specified name, manufacturer,
     * origin country.
     *
     * @param name          the name of the rocket
     * @param manufacturer  the manufacturer of the rocket
     * @param originCountry the country of origin of the rocket
     * @throws IllegalArgumentException if any of the parameters
     *                                  are empty or null
     */
    public Rocket(final String name,
                  final String manufacturer, final String originCountry)
            throws IllegalArgumentException {
        this.name = RequirementHandler
                .requireNonEmptyString(name);
        this.manufacturer = RequirementHandler
                .requireNonEmptyString(manufacturer);
        this.originCountry = RequirementHandler
                .requireNonEmptyString(originCountry);
        this.stages = new LinkedList<>();
        this.cone = new NoseCone(
                new ComponentBase(
                        "Example Nose Cone",
                        80, 150, 200,
                        Material.ALUMINIUM),
                30,
                NoseConeShape.CONICAL);
    }

    /**
     * Compares the two rockets by summary weights values.
     *
     * @param o the rocket to be compared with
     * @return zero if summary weights are equals,
     * negative if summary weight of this rocket is less,
     * than the weight of specified one, otherwise, returns
     * a positive number
     */
    @Override
    public int compareTo(Rocket o) {
        return Double.compare(this.getWeight(), o.getWeight());
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
    public void addStage(Stage stage) {
        this.stages.add(stage);
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
