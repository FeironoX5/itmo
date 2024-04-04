package BattleOfKanto.moves;

import ru.ifmo.se.pokemon.*;

public class Facade extends PhysicalMove {
    public Facade() {
        super(Type.NORMAL, 70, 100);
    }

    private boolean flag;

    @Override
    public void applyOppDamage(Pokemon p, double damage) {
        Status defStatus = p.getCondition();
        if (defStatus.equals(Status.POISON) || defStatus.equals(Status.BURN) || defStatus.equals(Status.PARALYZE)) {
            p.setMod(Stat.HP, 2 * (int) damage);
            flag = true;
        } else {
            p.setMod(Stat.HP, (int) damage);
            flag = false;
        }
    }

    @Override
    protected String describe() {
        if (flag) return "больно бьет";
        else return "бьет";
    }
}