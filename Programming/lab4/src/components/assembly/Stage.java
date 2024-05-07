package components.assembly;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import components.Vector;
import components.body.BodyComponent;
import utils.ComponentBase;
import utils.RequirementHandler;

public class Stage extends AssemblyComponent {
    private final LinkedList<BodyComponent> body;

    public Stage(final ComponentBase componentBase,
            final LinkedList<BodyComponent> body)
            throws IllegalArgumentException {
        super(componentBase);
        this.body = RequirementHandler.requireNonEmptyArray(body);
    }

    public LinkedList<BodyComponent> getBody() {
        return body;
    }

    public Vector calculateMovement() {
        Vector vector = new Vector(0, 0, 0);
        for (BodyComponent c : getBody()) {
            vector.set(Vector.add(vector, c.calculateMovement()));
        }
        return vector;
    }

    @Override
    public double getWidth() {
        return Collections.max(body, Comparator.comparing(bodyComponent -> bodyComponent.getWidth())).getWidth();
    }

    @Override
    public double getHeight() {
        return body.stream().map(BodyComponent::getHeight).mapToDouble(Double::doubleValue).sum();
    }

    @Override
    public double getWeight() {
        return body.stream().map(BodyComponent::getWeight).mapToDouble(Double::doubleValue).sum()
                + this.weight;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder(String.format("%s\n", name));
        for (BodyComponent c : getBody()) {
            res.append(c);
        }
        return res.toString();
    }
}
