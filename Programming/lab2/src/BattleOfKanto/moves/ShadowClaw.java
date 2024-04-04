package BattleOfKanto.moves;

import ru.ifmo.se.pokemon.*;

public class ShadowClaw extends PhysicalMove {

    public ShadowClaw() {
        super(Type.GHOST, 70, 100);

    }

    @Override
    protected double calcCriticalHit(Pokemon att,
                                     Pokemon def) {
        return 2.125;
    }

    @Override
    protected String describe() {
        return "ударил клыком";
    }
}