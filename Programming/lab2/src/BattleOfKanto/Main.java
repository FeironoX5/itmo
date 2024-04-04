package BattleOfKanto;

import ru.ifmo.se.pokemon.*;
import BattleOfKanto.pockemons.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("==============================================\nПриветствуем всех на БОЕ В КАНТО!\nНапоминаем, что это сражение проходит лишь раз\nв тысячелетие и нужно чтобы определить правителя\nнаших земель на следующие 10 веков.\nЖелаем чемпионам славной битвы и НАЧИНАЕМ БОЙ!\n==============================================\n");

        Battle b = new Battle();

        Barboach p1 = new Barboach("Барбочонок", 1);
        Mimikyu p2 = new Mimikyu("Обдолбыш-пикачу", 2);
        Whiscash p3 = new Whiscash("Вискас", 2);
        NidoranM p4 = new NidoranM("Ушастый", 1);
        Nidorino p5 = new Nidorino("Злой Ушастый", 3);
        Nidoking p6 = new Nidoking("Злой Ушастый с ногами", 1);

        b.addAlly(p1);
        b.addAlly(p2);
        b.addAlly(p3);
        b.addFoe(p4);
        b.addFoe(p5);
        b.addFoe(p6);
        b.go();
    }
}