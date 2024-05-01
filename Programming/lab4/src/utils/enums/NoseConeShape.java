package utils.enums;

/**
 * Форма {@link components.assembly.NoseCone}.
 *
 * @author Gleb Kiva
 */
public enum NoseConeShape {
    /**
     * Проекция конической {@link NoseCone} сбоку выглядит как треугольник.
     */
    CONICAL,
    /**
     * Проекция эллипсоидной {@link NoseCone} сбоку выглядит как полуэллипс.
     */
    ELLIPSOID,
    /**
     * Проекция аэродинамической {@link NoseCone} сбоку выглядит как
     * треугольник, где боковые грани выпуклы и имеют определённое скругление.
     */
    OGIVE;
}
