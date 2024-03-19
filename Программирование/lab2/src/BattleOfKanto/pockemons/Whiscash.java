package BattleOfKanto.pockemons;

import BattleOfKanto.moves.*;

public class Whiscash extends Nidorino {
    public Whiscash(String name, int level) {
        super(name, level);
        setStats(110, 78, 73, 76, 71, 60);
        setMove(new Tickle());
    }
}
