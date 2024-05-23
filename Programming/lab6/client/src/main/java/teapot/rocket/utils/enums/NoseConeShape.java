package teapot.rocket.utils.enums;

/**
 * Represents the shape of a nose cone used in rocket assemblies.
 *
 * <p>
 * Each enum constant describes how the projection of the corresponding nose cone
 * looks from the side, providing a visual description of its shape.
 * </p>
 *
 * <p>
 * This enum is used to specify and manage different nose cone shapes within
 * the rocket assembly system.
 * </p>
 *
 * @author Gleb Kiva
 */
public enum NoseConeShape {

    /**
     * A conical nose cone shape appears as a triangle when viewed from the side.
     */
    CONICAL,

    /**
     * An ellipsoidal nose cone shape appears as a semi-ellipse when viewed from the side.
     */
    ELLIPSOID,

    /**
     * An ogive nose cone shape appears as a rounded triangle when viewed from the side,
     * with convex lateral surfaces and specific curvature.
     */
    OGIVE;
}
