package teapot_rocket;

import java.util.LinkedList;
import java.util.Scanner;

import teapot_rocket.components.Rocket;
import teapot_rocket.components.assembly.*;
import teapot_rocket.components.body.*;
import teapot_rocket.components.inner.*;
import teapot_rocket.utils.ComponentBase;
import teapot_rocket.utils.enums.*;
import teapot_rocket.utils.exceptions.*;

/**
 * The main application class for simulating a rocket launch scenario.
 * Initializes a rocket with components and simulates the launch sequence.
 * Handles exceptions related to rocket initialization and control.
 * Provides a command-line interface for user interaction.
 * <p>
 * This class demonstrates the use of rocket components and engines,
 * including setting velocities, stage separation, and maneuvering.
 * Exception handling ensures robustness during rocket initialization and
 * launch.
 * </p>
 *
 * @author Gleb Kiva
 */
public final class App {
    /**
     * Initializes empty App.
     */
    private App() {
    }

    /**
     * Main method for launching the rocket simulation.
     * Creates a rocket, initializes its components, and simulates the launch
     * sequence.
     * Handles exceptions that may occur during rocket initialization or control.
     * 
     * @param args The command-line arguments (not used in this application).
     * @throws InterruptedException If the thread is interrupted during the
     *                              simulation.
     */
    public static void main(final String[] args) throws InterruptedException {
        Rocket rocket;
        Scanner s = new Scanner(System.in);
        try {
            // Create a rocket with specified components
            rocket = new Rocket("Skibider-412Y", "Dop X", "Пакистан",
                    new LinkedList<Stage>(),
                    new NoseCone(
                            new ComponentBase("PNS-3.90", 11.32, 15, 15, Material.ALUMINIUM),
                            8.5, NoseConeShape.ELLIPSOID));

            // Display characteristics and structure of the created rocket
            System.out.printf("\u001B[1m=== ХАРАКТЕРИСТИКИ СОЗДАННОЙ РАКЕТЫ ===\u001B[0m\n%s\n",
                    rocket.getProperties());
            System.out.printf("\u001B[1m=== СТРУКТУРА СОЗДАННОЙ РАКЕТЫ ===\u001B[0m\n%s\n", rocket);

            // Prompt user to initiate launch sequence
            System.out.println("\u001B[1mНажмите ENTER чтобы продолжить.....\u001B[0m\n");
            s.nextLine();

            // Simulate countdown and launch sequence
            System.out.println("Тогда взлетаем!");
            Thread.sleep(1000);
            System.out.println("3......");
            Thread.sleep(1000);
            System.out.println("2....");
            Thread.sleep(1000);
            System.out.println("1..");
            Thread.sleep(1000);

            // Set velocity of the rocket's inner engine
            try {
                ((InnerEngine) rocket.getStages().get(0).getBody().get(0).getInners().get(0)).setVelocity(5400);
            } catch (NonPositiveNumberException e) {
                System.err.println("Обнаружена неправильная скорость ракеты:");
                System.err.println(e.getMessage());
            }

            // Display current movement vector of the rocket
            Thread.sleep(200);
            System.out.printf("\u001B[1mТЕКУЩИЙ ВЕКТОР РАКЕТЫ: %s\u001B[0m\n", rocket.calculateMovement());

            // Simulate stage separation and engine adjustments
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

            // Rotate the rocket's body tube
            ((BodyTube) rocket.getStages().get(0).getBody().get(0)).rotate(30);
            System.out.printf("\u001B[1mТЕКУЩИЙ ВЕКТОР РАКЕТЫ: %s\u001B[0m\n", rocket.calculateMovement());
            Thread.sleep(1700);

            // Adjust engine velocity and simulate out-of-control scenario
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
