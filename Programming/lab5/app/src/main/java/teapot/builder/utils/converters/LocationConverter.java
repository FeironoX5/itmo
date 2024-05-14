package teapot.builder.utils.converters;

import java.util.ArrayList;

import teapot.builder.models.Location;
import teapot.builder.utils.interfaces.Converter;

/**
 * Converter implementation for encoding and decoding Location objects to and
 * from CSV format.
 *
 * @author Gleb Kiva
 */
public final class LocationConverter implements Converter<Location> {
    /**
     * Constructs a RouteBaseConverter instance.
     */
    public LocationConverter() {
    }

    /**
     * Encodes a Location object into a CSV string representation.
     *
     * @param location The Location object to encode.
     * @return The CSV string representing the encoded Location.
     */
    @Override
    public String encode(Location location) {
        ArrayList<String> values = new ArrayList<>();
        values.add(Long.toString(location.getX()));
        values.add(Float.toString(location.getY()));
        values.add(location.getName());
        return String.join(";", values);
    }

    /**
     * Decodes CSV data into a Location object.
     *
     * @param args The array of string arguments representing CSV data.
     * @return The decoded Location object.
     * @throws NumberFormatException If the CSV data cannot be parsed into valid
     *                               numeric values.
     */
    @Override
    public Location decode(String... args) {
        int i = 0;
        return new Location(
                Long.parseLong(args[i++]),
                Float.parseFloat(args[i++]),
                args[i++]);
    }
}
