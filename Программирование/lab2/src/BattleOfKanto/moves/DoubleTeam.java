package BattleOfKanto.moves;

import ru.ifmo.se.pokemon.*;

public class DoubleTeam extends StatusMove {
    public DoubleTeam() {
        super(Type.NORMAL, 0, 0);
    }

    @Override
    public void applySelfEffects(Pokemon p) {
        Effect eff = new Effect();
        eff = eff.stat(Stat.EVASION, +1);
        eff = eff.turns(1);
        p.addEffect(eff);
    }

    @Override
    protected String describe() {
        return "уклончивым становится";
    }
}