package BattleOfKanto.pockemons;

import BattleOfKanto.moves.*;
import ru.ifmo.se.pokemon.*;

public class NidoranM extends Pokemon {
    public NidoranM(String name, int level) {
        super(name, level);
        setStats(46, 57, 40, 40, 40, 50);
        setType(Type.POISON);
        setMove(new Venoshock(), new Rest());
    }
}
