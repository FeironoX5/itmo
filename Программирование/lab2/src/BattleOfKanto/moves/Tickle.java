package BattleOfKanto.moves;

import ru.ifmo.se.pokemon.*;

public class Tickle extends StatusMove {
    public Tickle() {
        super(Type.NORMAL, 0, 0);
    }

    @Override
    public void applyOppEffects(Pokemon p) {
        Effect effAttack = new Effect().turns(1);
        effAttack = effAttack.stat(Stat.ATTACK, (int) (p.getStat(Stat.ATTACK) - 1));
        Effect effDefense = new Effect().turns(1);
        effDefense = effDefense.stat(Stat.DEFENSE, (int) (p.getStat(Stat.DEFENSE) - 1));
        p.addEffect(effAttack);
        p.addEffect(effDefense);
    }

    @Override
    protected String describe() {
        return "щекочет";
    }
}