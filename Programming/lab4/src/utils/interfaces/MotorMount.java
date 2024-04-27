package utils.interfaces;

import components.Vector;
import utils.exceptions.NaturalNumberException;

/**
 * Интерфейс, предназначенный для реализации двигателей ракеты.
 *
 * @author Gleb Kiva
 */
public interface MotorMount {
    /**
     * Задаёт двигателю мощность.
     *
     * @param velocity Мощность (ватт/ньютон тяги)
     * @throws NaturalNumberException
     */
    void setVelocity(double velocity) throws NaturalNumberException;

    /**
     * Возвращает мощность двигателя.
     *
     * @return Мощность (ватт/ньютон тяги)
     */
    double getVelocity();

    /**
     * Возвращает направление двигателя.
     *
     * @return Вектор направления
     */
    Vector getVector();

    /**
     * Возвращает вектор движения, создаваемого
     * двигателем.
     *
     * @return Вектор движения
     */
    Vector calculateMovement();
}
