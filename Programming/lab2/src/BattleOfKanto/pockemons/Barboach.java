package BattleOfKanto.pockemons;

import BattleOfKanto.moves.*;
import ru.ifmo.se.pokemon.*;

public class Barboach extends Pokemon {
    public Barboach(String name, int level) {
        super(name, level);
        setStats(50, 48, 43, 46, 41, 60);
        setType(Type.WATER, Type.GROUND);
        setMove(new Rest(), new Facade(), new DoubleTeam());
    }
}