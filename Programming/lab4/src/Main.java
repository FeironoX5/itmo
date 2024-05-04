import components.Rocket;
import components.Fin;
import components.assembly.NoseCone;
import components.assembly.Stage;
import components.body.BodyComponent;
import components.body.BodyTube;
import components.body.Transition;
import components.inner.CenteringRing;
import components.inner.InnerComponent;
import components.inner.InnerEngine;
import components.inner.ManoeuvringEngine;
import components.inner.Trapezoidal;
import components.mass.MassComponent;
import components.mass.Parachute;
import utils.enums.FinShape;
import utils.enums.Material;
import utils.enums.NoseConeShape;
import utils.enums.TransitionShape;
import utils.exceptions.NameException;
import utils.exceptions.NaturalNumberException;
import utils.exceptions.NumberException;
import utils.exceptions.StageNotExistsException;
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
          new Stage[] {
              new Stage("STG-1", 4, Material.ALUMINIUM, new BodyComponent[] {
                  new Transition("Переход 1", 8.2,
                      Material.ALUMINIUM,
                      new InnerComponent[] {
                          new MassComponent(
                              "Отсек для пилота",
                              15,
                              Material.STEEL),
                          new Parachute("Парашют для эвакуации",
                              1,
                              Material.SILK,
                              6.4,
                              100)
                      }, 7.5, 10.2, 8.5,
                      TransitionShape.CONICAL),
                  new BodyTube("Труба 1", 10, Material.ALUMINIUM,
                      new InnerComponent[] {
                          new ManoeuvringEngine(
                              "Правый рулевой двигатель",
                              10,
                              Material.ALUMINIUM,
                              -30),
                          new ManoeuvringEngine(
                              "Левый рулевой двигатель",
                              10,
                              Material.ALUMINIUM,
                              30)
                      }, 7.62, 10.2, 0.127),
                  new Transition("Переход 2", 5.2,
                      Material.ALUMINIUM,
                      new InnerComponent[] {}, 4, 14,
                      10.2, TransitionShape.CONICAL),
                  new BodyTube("Труба 2", 12.5,
                      Material.ALUMINIUM,
                      new InnerComponent[] {
                          new MassComponent(
                              "Полезный груз 1",
                              1.2,
                              Material.STEEL),
                          new CenteringRing(
                              "Перегородка",
                              0.5,
                              Material.ALUMINIUM,
                              14,
                              0.5),
                          new MassComponent(
                              "Полезный груз 2",
                              1.2,
                              Material.STEEL),
                      }, 14, 14, 0.2),
                  new BodyTube("Труба 3", 21, Material.ALUMINIUM,
                      new InnerComponent[] {
                          new InnerEngine(
                              "Вспомогательный двигатель",
                              35,
                              Material.ALUMINIUM,
                              14,
                              1),
                      }, 20, 14, 0.51),
              }),
              new Stage("STG-2", 10, Material.ALUMINIUM, new BodyComponent[] {
                  new Transition("Переход 1", 5.2,
                      Material.ALUMINIUM,
                      new InnerComponent[] {}, 8, 20,
                      14, TransitionShape.ELLIPSOID),
                  new BodyTube("Труба 1", 39, Material.ALUMINIUM,
                      new InnerComponent[] {
                          new MassComponent(
                              "Топливо",
                              90,
                              Material.FUEL),
                          new Trapezoidal("Крылья",
                              1.5,
                              Material.STEEL,
                              new Fin[] {
                                  new Fin("Крыло 1",
                                      0.23,
                                      Material.ALUMINIUM,
                                      FinShape.ROUNDED,
                                      10),
                                  new Fin("Крыло 2",
                                      0.23,
                                      Material.ALUMINIUM,
                                      FinShape.ROUNDED,
                                      10),
                                  new Fin("Крыло 3",
                                      0.23,
                                      Material.ALUMINIUM,
                                      FinShape.ROUNDED,
                                      10)
                              },
                              30)
                      }, 30, 20, 1.6),
                  new BodyTube("Труба 2", 50, Material.ALUMINIUM,
                      new InnerComponent[] {
                          new InnerEngine("Главный двигатель",
                              65,
                              Material.ALUMINIUM,
                              20,
                              1),
                          new MassComponent(
                              "Топливо",
                              120,
                              Material.FUEL)
                      }, 45, 20, 2.2),
              }),
          },
          new NoseCone("PNS-3.90", 11.32, Material.ALUMINIUM, 8.5, 32.4, 0.152,
              NoseConeShape.ELLIPSOID));
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
        ((InnerEngine) rocket.getStages()[1].getBody()[2].getInners()[0]).setVelocity(5400);
      } catch (NaturalNumberException e) {
        System.err.println("Обнаружена неправильная скорость ракеты:");
        System.err.println(e.getMessage());
      }
      Thread.sleep(200);
      System.out.printf("\u001B[1mТЕКУЩИЙ ВЕКТОР РАКЕТЫ: %s\u001B[0m\n", rocket.calculateMovement());
      Thread.sleep(1500);
      rocket.separateStage();
      System.out.printf("\u001B[1mТЕКУЩИЙ ВЕКТОР РАКЕТЫ: %s\u001B[0m\n", rocket.calculateMovement());
      Thread.sleep(1600);
      ((ManoeuvringEngine) rocket.getStages()[0].getBody()[1].getInners()[0]).setVelocity(1000);
      System.out.printf("\u001B[1mТЕКУЩИЙ ВЕКТОР РАКЕТЫ: %s\u001B[0m\n", rocket.calculateMovement());
      Thread.sleep(1500);
      ((ManoeuvringEngine) rocket.getStages()[0].getBody()[1].getInners()[1]).setVelocity(1000);
      System.out.printf("\u001B[1mТЕКУЩИЙ ВЕКТОР РАКЕТЫ: %s\u001B[0m\n", rocket.calculateMovement());
      Thread.sleep(1500);
      ((BodyTube) rocket.getStages()[0].getBody()[1]).rotate(30);
      System.out.printf("\u001B[1mТЕКУЩИЙ ВЕКТОР РАКЕТЫ: %s\u001B[0m\n", rocket.calculateMovement());
      Thread.sleep(1700);
      ((InnerEngine) rocket.getStages()[0].getBody()[4].getInners()[0]).setVelocity(3000);
      System.out.printf("\u001B[1mТЕКУЩИЙ ВЕКТОР РАКЕТЫ: %s\u001B[0m\n", rocket.calculateMovement());
      Thread.sleep(1500);
      System.out.printf("\u001B[1mАЛО вы слишком далеко... мы теряем конта#@()!....\u001B[0m\n");
      Thread.sleep(1300);
      System.out.printf("\u001B[1m$*$#()@$\u001B[0m\n");
      // ((BodyTube) rocket.getStages()[0].getBody()[1]).rotate(30);
      // rocket.getStages()[0].getBody()[1].rotate();

    } catch (NameException e) {
      System.err.println("Обнаружены ошибки в имени:");
      System.err.println(e.getMessage());
    } catch (NaturalNumberException e) {
      System.err.println("Обнаружены ошибки в натуральных циферках:");
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
