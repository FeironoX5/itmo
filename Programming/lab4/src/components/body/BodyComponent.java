package components.body;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import components.Component;
import components.Vector;
import components.inner.InnerComponent;
import utils.ComponentBase;
import utils.RequirementHandler;
import utils.interfaces.MotorMount;

public abstract class BodyComponent extends Component {
    private final LinkedList<InnerComponent> inners;

    public BodyComponent(final ComponentBase componentBase,
            final LinkedList<InnerComponent> inners)
            throws IllegalArgumentException {
        super(componentBase);
        this.inners = RequirementHandler.requireNonEmptyArray(inners);
    }

    public Vector calculateMovement() {
        Vector vector = new Vector(0, 0, 0);
        for (InnerComponent c : getInners()) {
            if (c instanceof MotorMount) {
                vector.set(Vector.add(vector, ((MotorMount) c).calculateMovement()));
            }
        }
        return vector;
    }

    public LinkedList<InnerComponent> getInners() {
        return inners;
    }

    @Override
    public double getWidth() {
        return Collections.max(inners, Comparator.comparing(inner -> inner.getWidth())).getWidth();
    }

    @Override
    public double getHeight() {
        return Collections.max(inners, Comparator.comparing(inner -> inner.getHeight())).getWidth();
    }

    @Override
    public double getWeight() {
        return inners.stream().map(InnerComponent::getWeight).mapToDouble(Double::doubleValue).sum()
                + this.weight;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder(String.format("| |_Основной%s %s\n", name));
        for (InnerComponent c : getInners()) {
            res.append(c);
        }
        return res.toString();
    }
}
