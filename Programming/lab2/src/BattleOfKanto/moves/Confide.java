package BattleOfKanto.moves;

import ru.ifmo.se.pokemon.*;

public class Confide extends StatusMove {
    public Confide() {
        super(Type.NORMAL, 0, 0);
    }

    @Override
    public void applyOppEffects(Pokemon p) {
        Effect eff = new Effect().stat(Stat.SPECIAL_ATTACK, -1).turns(1);
        p.addEffect(eff);
    }

    @Override
    protected String describe() {
        return "конфайдит";
    }
}