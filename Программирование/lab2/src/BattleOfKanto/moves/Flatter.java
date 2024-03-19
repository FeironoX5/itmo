package BattleOfKanto.moves;

import ru.ifmo.se.pokemon.*;

public class Flatter extends StatusMove {

    public Flatter() {
        super(Type.DARK, 0, 100);

    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        p.confuse();
        p.setMod(Stat.SPECIAL_ATTACK, +1);
    }

    @Override
    protected String describe() {
        return "законфузил";
    }
}