package teapot.builder.utils.converters;

import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.TimeZone;

import teapot.builder.models.Route;
import teapot.builder.utils.interfaces.Converter;

public class RouteConverter implements Converter<Route> {
    private TimeZone CURRENT_TIMEZONE;
    public static final RouteConverter instance = new RouteConverter(TimeZone.getDefault());
    public static final CoordinatesConverter coordinatesConverter = new CoordinatesConverter();
    public static final LocationConverter locationConverter = new LocationConverter();

    public RouteConverter(TimeZone CURRENT_TIMEZONE) {
        this.CURRENT_TIMEZONE = CURRENT_TIMEZONE;
    }

    @Override
    public String encode(Route route) {
        ArrayList<String> values = new ArrayList<>();
        values.add(Integer.toString(route.getId()));
        values.add(route.getName());
        values.add(coordinatesConverter.encode(route.getCoordinates()));// FIXME replace with coordinates converter
        values.add(new SimpleDateFormat().format(route.getCreationDate()));
        values.add(locationConverter.encode(route.getTo())); // TODO Timezone??
        values.add(locationConverter.encode(route.getFrom()));
        values.add(Long.toString(route.getDistance()));
        return String.join(";", values);
    }

    @Override
    public Route decode(String... args) {
        var i = 0;
        return new Route(
                args[i++],
                coordinatesConverter.decode(args[i++], args[i++]),
                locationConverter.decode(args[i++], args[i++], args[i++]),
                locationConverter.decode(args[i++], args[i++], args[i++]),
                Long.parseLong(args[i++]));
    }

    public void setTimeZone(TimeZone timeZone) {
        CURRENT_TIMEZONE = timeZone;

    }

}
