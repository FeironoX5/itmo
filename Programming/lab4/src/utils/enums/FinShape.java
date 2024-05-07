package utils.enums;

/**
 * Represents the shape of a {@link Fin}.
 *
 * <p>
 * The {@code FinShape} enum defines different shapes that a fin can have,
 * specifying how the edges of the fin are configured.
 * </p>
 *
 * <p>
 * Each enum constant in {@code FinShape} represents a specific shape:
 * </p>
 *
 * <ul>
 *     <li>{@link #SQUARE}: A fin with sharp corners.</li>
 *     <li>{@link #ROUNDED}: A fin with rounded corners.</li>
 * </ul>
 *
 * <p>
 * The {@code FinShape} enum is used to specify the shape of fins attached to rockets,
 * providing flexibility in fin design and configuration.
 * </p>
 *
 * <p>
 * This enum is authored by Gleb Kiva.
 * </p>
 *
 * @author Gleb Kiva
 * @see Fin
 */
public enum FinShape {
    /**
     * A fin with sharp corners.
     */
    SQUARE,

    /**
     * A fin with rounded corners.
     */
    ROUNDED
}
