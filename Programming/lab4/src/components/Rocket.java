package components;

import components.assembly.NoseCone;
import components.assembly.Stage;
import utils.RequirementHandler;
import utils.enums.EventType;
import utils.exceptions.EmptyArrayException;
import utils.exceptions.EmptyStringException;
import utils.exceptions.StageNotExistsException;
import utils.implementations.Event;
import utils.implementations.EventBus;
import utils.interfaces.EventListener;
import utils.interfaces.Physical;
import static java.lang.Double.max;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Objects;

/**
 * {@code Rocket} это ракета, которая отправится в долгий полёт.
 *
 * @author Gleb Kiva
 */
public final class Rocket implements Physical {
    /**
     * Название ракеты,
     */
    public final String name;
    public final String manufacturer;
    public final String originCountry;
    private final LinkedList<Stage> stages;
    public final NoseCone cone;
    public static EventBus eventBus = new EventBus();

    // TODO implement component adding like:
    // public <T extends Weapon> T
    // produce() { ... }

    public Rocket(final String name,
            final String manufacturer, final String originCountry,
            final LinkedList<Stage> stages, final NoseCone cone)
            throws EmptyStringException, EmptyArrayException {
        this.name = RequirementHandler.requireNonEmptyString(name);
        this.manufacturer = RequirementHandler.requireNonEmptyString(manufacturer);
        this.originCountry = RequirementHandler.requireNonEmptyString(originCountry);
        this.stages = RequirementHandler.requireNonEmptyArray(stages);
        this.cone = cone;
    }

    public class RocketStatusHandler implements EventListener {
        // TODO replace all prints with events
        // TODO add conscructor and subscribe to events
        @Override
        public void handle(final Event<?> event) {

        }

    }

    public Vector calculateMovement() {
        Vector vector = new Vector(0, 0, 0);
        // FIXME for (int i = 0; i < getActiveStages(); i++) {
        // vector.set(Vector.add(vector, getStage(i).calculateMovement()));
        // }
        return Vector.multiply(vector, -1);
    }

    public void separateStage() throws StageNotExistsException {
        if (stages.size() == 0) {
            throw new StageNotExistsException();
        }
        this.stages.removeLast();
        System.out.printf("Ступень %d успешно отсоединена\n", stages.size() + 1);
        eventBus.notifyListeners(new Event<Integer>(stages.size() + 1, EventType.STAGE_SEPARATED));
    }

    public String getProperties() {
        return String.format(
                "Название: %s\nПроизводитель: %s\nСтрана производства: %s\nКоличество ступеней: %d\nВес: %f\nДиаметр: %f\nВысота: %f",
                name, manufacturer, originCountry, getStages().size(), getWeight(), getWidth(), getHeight());
    }

    public LinkedList<Stage> getStages() {
        return stages;
    }

    @Override
    public double getWidth() {
        return max(
                Collections.max(stages, Comparator.comparing(stage -> stage.getWidth())).getWidth(),
                cone.width);
    }

    @Override
    public double getHeight() {
        return stages.stream().map(Stage::getHeight).mapToDouble(Double::doubleValue).sum()
                + cone.getHeight();
    }

    @Override
    public double getWeight() {
        return stages.stream().map(Stage::getWeight).mapToDouble(Double::doubleValue).sum()
                + cone.getWeight();
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
        String res = "";
        res += String.format("%s\n", name);
        res += cone.toString();
        for (int i = 0; i < stages.size(); i++) {
            res += stages.get(i).toString();
        }
        res += "|_|_|_________";
        return res;
    }
}
