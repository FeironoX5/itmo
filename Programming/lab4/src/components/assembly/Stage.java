package components.assembly;

import components.Vector;
import components.body.BodyComponent;
import enums.Material;
import exceptions.NameException;
import exceptions.NumberException;

public class Stage extends AssemblyComponent {
    private final BodyComponent[] body;

    public Stage(String name, double mass, Material material, BodyComponent[] body)
            throws NameException, NumberException {
        super(name, mass, material);
        this.body = body;
    }

    public BodyComponent[] getBody() {
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
    public String toString() {
        String res = "";
        res += String.format("|_%s %s\n", getType(), getName());
        for (BodyComponent c : getBody()) {
            res += c.toString();
        }
        return res;
    }
}
