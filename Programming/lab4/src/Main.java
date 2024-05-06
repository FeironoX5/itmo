import components.Rocket;
import components.assembly.*;
import components.body.*;
import components.inner.*;
import utils.enums.*;
import utils.exceptions.*;
import utils.ComponentBase;

import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    /**
     * Главный метод, создающий ракету и отправляющий
     * её в долгий полёт.
     *
     * @param args Аргументы из командной строки
     * @throws InterruptedException
     */
    public static void main(final String[] args) throws InterruptedException {
        Rocket rocket;
        Scanner s = new Scanner(System.in);
        try {
            rocket = new Rocket("Skibider-412Y", "Dop X", "Пакистан",
                    new LinkedList<Stage>(),
                    new NoseCone(
                            new ComponentBase("PNS-3.90", 11.32, 15, 15, Material.ALUMINIUM),
                            8.5, NoseConeShape.ELLIPSOID));
            System.out.printf("\u001B[1m=== ХАРАКТЕРИСТИКИ СОЗДАННОЙ РАКЕТЫ ===\u001B[0m\n%s\n",
                    rocket.getProperties());
            System.out.printf("\u001B[1m=== СТРУКТУРА СОЗДАННОЙ РАКЕТЫ ===\u001B[0m\n%s\n", rocket);
            System.out.printf("\u001B[1m=== НАЧАЛЬНЫЙ ВЕКТОР ===\u001B[0m\n%s\n",
                    rocket.calculateMovement());
            System.out.println("\u001B[1mНажмите ENTER чтобы продолжить.....\u001B[0m\n");
            s.nextLine();
            System.out.println("Тогда взлетаем!");
            Thread.sleep(1000);
            System.out.println("3......");
            Thread.sleep(1000);
            System.out.println("2....");
            Thread.sleep(1000);
            System.out.println("1..");
            Thread.sleep(1000);
            try {
                ((InnerEngine) rocket.getStages().get(0).getBody().get(0).getInners().get(0)).setVelocity(5400);
            } catch (NonPositiveNumberException e) {
                System.err.println("Обнаружена неправильная скорость ракеты:");
                System.err.println(e.getMessage());
            }
            Thread.sleep(200);
            System.out.printf("\u001B[1mТЕКУЩИЙ ВЕКТОР РАКЕТЫ: %s\u001B[0m\n", rocket.calculateMovement());
            Thread.sleep(1500);
            rocket.separateStage();
            System.out.printf("\u001B[1mТЕКУЩИЙ ВЕКТОР РАКЕТЫ: %s\u001B[0m\n", rocket.calculateMovement());
            Thread.sleep(1600);
            ((ManoeuveringEngine) rocket.getStages().get(0).getBody().get(0).getInners().get(0)).setVelocity(1000);
            System.out.printf("\u001B[1mТЕКУЩИЙ ВЕКТОР РАКЕТЫ: %s\u001B[0m\n", rocket.calculateMovement());
            Thread.sleep(1500);
            ((ManoeuveringEngine) rocket.getStages().get(0).getBody().get(0).getInners().get(0)).setVelocity(1000);
            System.out.printf("\u001B[1mТЕКУЩИЙ ВЕКТОР РАКЕТЫ: %s\u001B[0m\n", rocket.calculateMovement());
            Thread.sleep(1500);
            ((BodyTube) rocket.getStages().get(0).getBody().get(0)).rotate(30);
            System.out.printf("\u001B[1mТЕКУЩИЙ ВЕКТОР РАКЕТЫ: %s\u001B[0m\n", rocket.calculateMovement());
            Thread.sleep(1700);
            ((InnerEngine) rocket.getStages().get(0).getBody().get(0).getInners().get(0)).setVelocity(3000);
            System.out.printf("\u001B[1mТЕКУЩИЙ ВЕКТОР РАКЕТЫ: %s\u001B[0m\n", rocket.calculateMovement());
            Thread.sleep(1500);
            System.out.printf("\u001B[1mАЛО вы слишком далеко... мы теряем конта#@()!....\u001B[0m\n");
            Thread.sleep(1300);
            System.out.printf("\u001B[1m$*$#()@$\u001B[0m\n");

        } catch (EmptyStringException e) {
            System.err.println("Обнаружены ошибки в имени:");
            System.err.println(e.getMessage());
        } catch (EmptyArrayException e) {
            System.err.println("Обнаружены ошибки в наполнении компонентов:");
            System.err.println(e.getMessage());
        } catch (NonPositiveNumberException e) {
            System.err.println("Обнаружены ошибки в положительных циферках:");
            System.err.println(e.getMessage());
        } catch (NumberException e) {
            System.err.println("Обнаружены ошибки в циферках:");
            System.err.println(e.getMessage());
        } catch (StageNotExistsException e) {
            System.err.println("Отделяется несуществующая ступень ракеты:");
            System.err.println(e.getMessage());
        } finally {
            s.close();
        }
    }
}
