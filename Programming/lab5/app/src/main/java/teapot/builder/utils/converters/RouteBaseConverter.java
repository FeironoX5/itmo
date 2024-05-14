package teapot.builder.utils.converters;

import java.util.ArrayList;
import teapot.builder.models.Route;
import teapot.builder.utils.interfaces.Converter;

/**
 * Converter implementation for encoding and decoding Route.Base objects to and
 * from CSV format.
 *
 * @author Gleb Kiva
 */
public final class RouteBaseConverter implements Converter<Route.Base> {

    private final CoordinatesConverter coordinatesConverter = new CoordinatesConverter();
    private final LocationConverter locationConverter = new LocationConverter();

    /**
     * Encodes a Route.Base object into a CSV string representation.
     *
     * @param base The Route.Base object to encode.
     * @return The CSV string representing the encoded Route.Base.
     */
    @Override
    public String encode(Route.Base base) {
        ArrayList<String> values = new ArrayList<>();
        values.add(base.getName());
        values.add(coordinatesConverter.encode(base.getCoordinates()));
        values.add(locationConverter.encode(base.getTo()));
        values.add(locationConverter.encode(base.getFrom()));
        values.add(Long.toString(base.getDistance()));
        return String.join(";", values);
    }

    /**
     * Decodes CSV data into a Route.Base object.
     *
     * @param args The array of string arguments representing CSV data.
     * @return The decoded Route.Base object.
     * @throws NumberFormatException If the CSV data cannot be parsed into valid
     *                               numeric values.
     */
    @Override
    public Route.Base decode(String... args) {
        int i = 0;
        return new Route.Base(
                args[i++], // name
                coordinatesConverter.decode(args[i++], args[i++]), // coordinates
                locationConverter.decode(args[i++], args[i++], args[i++]), // to
                locationConverter.decode(args[i++], args[i++], args[i++]), // from
                Long.parseLong(args[i++]) // distance
        );
    }
}
