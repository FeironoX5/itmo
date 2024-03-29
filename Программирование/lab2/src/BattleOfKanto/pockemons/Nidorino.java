package BattleOfKanto.pockemons;

import BattleOfKanto.moves.*;
import ru.ifmo.se.pokemon.*;

public class Nidorino extends NidoranM {
    public Nidorino(String name, int level) {
        super(name, level);
        setStats(61, 72, 57, 55, 55, 65);
        setMove(new Flatter());
    }
}
