package BattleOfKanto.pockemons;

import BattleOfKanto.moves.*;
import ru.ifmo.se.pokemon.*;

public class Mimikyu extends Pokemon {
    public Mimikyu(String name, int level) {
        super(name, level);
        setStats(55, 90, 80, 50, 105, 96);
        setType(Type.GHOST, Type.FAIRY);
        setMove(new Confide(), new Discharge(), new DefenseCurl(), new Facade());
    }
}
