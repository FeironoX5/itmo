package teapot.builder.utils.interfaces;

/**
 * Interface for encoding and decoding objects of a specific type.
 *
 * @param <T> The type of object to encode and decode.
 *
 * @author Gleb Kiva
 */
public interface Converter<T> {

    /**
     * Encodes an object of type T into a string representation.
     *
     * @param object The object to encode.
     * @return The string representation of the encoded object.
     */
    String encode(T object);

    /**
     * Decodes a string representation into an object of type T.
     *
     * @param args The arguments needed for decoding the object.
     * @return The decoded object of type T.
     */
    T decode(String... args);
}
