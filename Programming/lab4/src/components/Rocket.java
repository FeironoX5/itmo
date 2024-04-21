package components;

import components.assembly.NoseCone;
import components.assembly.Stage;
import exceptions.NameException;
import exceptions.NaturalNumberException;
import exceptions.StageNotExistsException;
import services.DiameterService;
import services.HeightService;
import services.MassService;

import java.util.Objects;

public class Rocket {
    private final String name;
    private final String manufacturer;
    private final String originCountry;
    private final Stage[] stages;
    private final NoseCone cone;
    private long activeStages;

    public Rocket(String name, String manufacturer, String originCountry, Stage[] stages, NoseCone cone) throws NameException, NaturalNumberException {
        if (name.isEmpty()) {
            throw new NameException("Неправильное название ракеты");
        }
        this.name = name;
        if (manufacturer.isEmpty()) {
            throw new NameException("Неправильный производитель ракеты");
        }
        this.manufacturer = manufacturer;
        if (originCountry.isEmpty()) {
            throw new NameException("Неправильная страна производства ракеты");
        }
        this.originCountry = originCountry;
        if (stages.length <= 0) {
            throw new NaturalNumberException("Неправильное количество ступеней ракеты");
        }
        this.stages = stages;
        this.activeStages = stages.length;
        this.cone = cone;
    }

    public String getName() {
        return name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public Stage[] getStages() {
        return stages;
    }

    public NoseCone getCone() {
        return cone;
    }

    public Vector calculateMovement() {
        Vector vector = new Vector(0, 0, 0);
        for (int i = 0; i < getActiveStages(); i++) {
            vector.set(Vector.add(vector, getStage(i).calculateMovement()));
        }
        return Vector.multiply(vector, -1);
    }

    public void separateStage() throws StageNotExistsException {
        if (activeStages > 1) {
            System.out.printf("Ступень %d успешно отсоединена\n", this.activeStages);
            this.stages[this.stages.length - 1] = null;
            activeStages--;
        } else {
            throw new StageNotExistsException("Попытка отсоединения несуществующей ступени");
        }
    }

    public String getProperties() {
        return String.format(
                "Название: %s\nПроизводитель: %s\nСтрана производства: %s\nКоличество ступеней: %d\nВес: %f\nДиаметр: %f\nВысота: %f",
                getName(), getManufacturer(), getOriginCountry(), getStages().length, MassService.getMass(this),
                DiameterService.getDiameter(this), HeightService.getHeight(this));
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.toString());
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null || getClass() != object.getClass())
            return false;
        Rocket other = (Rocket) object;
        return Objects.equals(this.getName(), other.getName())
                && Objects.equals(this.getManufacturer(), other.getManufacturer())
                && Objects.equals(this.getOriginCountry(), other.getOriginCountry());
    }

    @Override
    public String toString() {
        String res = "";
        res += String.format("%s\n", getName());
        res += getCone().toString();
        for (int i = 0; i < getActiveStages(); i++) {
            res += getStage(i).toString();
        }
        res += "|_|_|_________";
        return res;
    }

    public long getActiveStages() {
        return activeStages;
    }

    public Stage getStage(int i) {
        return stages[i];
    }
}
