package teapot.builder.utils.converters;

import java.util.ArrayList;

import teapot.builder.models.Coordinates;
import teapot.builder.utils.interfaces.Converter;

/**
 * Converter implementation for encoding and decoding Coordinates objects to and
 * from CSV format.
 *
 * @author Gleb Kiva
 */
public final class CoordinatesConverter implements Converter<Coordinates> {

    /**
     * Encodes a Coordinates object into a CSV string representation.
     *
     * @param coordinates The Coordinates object to encode.
     * @return The CSV string representing the encoded Coordinates.
     */
    @Override
    public String encode(Coordinates coordinates) {
        ArrayList<String> values = new ArrayList<>();
        values.add(Integer.toString(coordinates.getX()));
        values.add(Double.toString(coordinates.getY()));
        return String.join(";", values);
    }

    /**
     * Decodes CSV data into a Coordinates object.
     *
     * @param args The array of string arguments representing CSV data.
     * @return The decoded Coordinates object.
     * @throws NumberFormatException If the CSV data cannot be parsed into valid
     *                               numeric values.
     */
    @Override
    public Coordinates decode(String... args) {
        int i = 0;
        return new Coordinates(
                Integer.parseInt(args[i++]),
                Double.parseDouble(args[i++]));
    }
}
