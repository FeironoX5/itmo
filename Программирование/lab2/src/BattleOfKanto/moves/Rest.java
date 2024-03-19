package BattleOfKanto.moves;

import ru.ifmo.se.pokemon.*;

public class Rest extends StatusMove {

    public Rest() {
        super(Type.PSYCHIC, 0, 0);
    }

    @Override
    protected void applySelfEffects(Pokemon p) {
        Effect eff = new Effect();
        eff = eff.condition(Status.SLEEP);
        eff = eff.turns(2);
        p.restore();
        p.addEffect(eff);
    }

    protected boolean checkAccuracy(Pokemon att, Pokemon def) {
        return true;
    }

    @Override
    protected String describe() {
        return "зачиллился";
    }
}