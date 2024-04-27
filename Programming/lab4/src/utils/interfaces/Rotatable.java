package utils.interfaces;

/**
 * Интерфейс, предназначенный для реализации
 * поворачивающийхся компонентов.
 *
 * @author Gleb Kiva
 */
public interface Rotatable {
    /**
     * Возвращает текущий поворот в градусах.
     *
     * @return Градус поворота компонента
     */
    double getRotation();

    /**
     * Поворачивает компонент на любое
     * количество градусов.
     *
     * @param angle
     */
    void rotate(double angle);
}
