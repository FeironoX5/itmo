package teapot.builder.utils.converters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TimeZone;
import java.time.ZonedDateTime;

import teapot.builder.models.Route;
import teapot.builder.utils.interfaces.Converter;

/**
 * Converter implementation for encoding and decoding {@link Route} objects to
 * and from CSV format.
 *
 * @author Gleb Kiva
 * 
 * @see RouteBaseConverter
 */
public final class RouteConverter implements Converter<Route> {
    /**
     * The current time zone used for conversions.
     */
    private TimeZone CURRENT_TIMEZONE; // TODO
    /**
     * Singleton instance of RouteConverter with default time zone.
     */
    public static final RouteConverter instance = new RouteConverter(TimeZone.getDefault());
    /**
     * Converter for {@link Route.Base} objects.
     */
    public final RouteBaseConverter routeBaseConverter = new RouteBaseConverter();

    /**
     * Constructs a RouteConverter instance with the specified timezone.
     *
     * @param CURRENT_TIMEZONE The timezone to use for date/time operations.
     */
    public RouteConverter(TimeZone CURRENT_TIMEZONE) {
        this.CURRENT_TIMEZONE = CURRENT_TIMEZONE;
    }

    /**
     * Encodes a Route object into a CSV string representation.
     *
     * @param route The Route object to encode.
     * @return The CSV string representing the encoded Route.
     */
    @Override
    public String encode(Route route) {
        ArrayList<String> values = new ArrayList<>();
        values.add(Integer.toString(route.getId()));
        values.add(route.getCreationDate().toString());
        values.add(routeBaseConverter.encode(route.getBase()));
        return String.join(";", values);
    }

    /**
     * Decodes CSV data into a Route object.
     *
     * @param args The array of string arguments representing CSV data.
     * @return The decoded Route object.
     * @throws NumberFormatException If the CSV data cannot be parsed into valid
     *                               numeric values.
     */
    @Override
    public Route decode(String... args) {
        int i = 0;
        return new Route(
                Integer.parseInt(args[i++]),
                ZonedDateTime.parse(args[i++]),
                routeBaseConverter.decode(Arrays.copyOfRange(args, i, args.length)));
    }

    /**
     * Sets the timezone to use for date/time operations.
     *
     * @param timeZone The timezone to set.
     */
    public void setTimeZone(TimeZone timeZone) {
        CURRENT_TIMEZONE = timeZone;
    }

    /**
     * Gets the current timezone used for date/time operations.
     *
     * @return The current timezone.
     */
    public TimeZone getTimeZone() {
        return CURRENT_TIMEZONE;
    }
}
