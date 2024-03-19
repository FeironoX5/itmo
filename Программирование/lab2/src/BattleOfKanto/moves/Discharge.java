package BattleOfKanto.moves;

import ru.ifmo.se.pokemon.*;

public class Discharge extends SpecialMove {
    public Discharge() {
        super(Type.ELECTRIC, 80, 100);
        Integer x = null;
    }

    @Override
    public void applyOppEffects(Pokemon p) {
        if (Math.random() <= 0.3) {
            Effect.paralyze(p);
        }
    }

    @Override
    protected String describe() {
        return "бьёт шокером";
    }
}