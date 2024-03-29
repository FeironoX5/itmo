package BattleOfKanto.moves;

import ru.ifmo.se.pokemon.*;

public class Venoshock extends SpecialMove {
    public Venoshock() {
        super(Type.POISON, 65, 100);
    }

    private boolean flag;

    @Override
    public void applyOppDamage(Pokemon p, double damage) {
        Status cond = p.getCondition();
        if (cond.equals(Status.POISON)) {
            flag = true;
            p.setMod(Stat.HP, 2 * (int) damage);
        } else {
            p.setMod(Stat.HP, (int) damage);
        }
    }

    @Override
    protected String describe() {
        if (flag) return "больно бьет";
        else return "бьет";
    }
}