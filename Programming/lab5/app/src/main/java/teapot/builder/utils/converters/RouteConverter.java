package teapot.builder.utils.converters;

import java.util.ArrayList;
import java.util.TimeZone;
import java.time.ZonedDateTime;

import teapot.builder.models.ComparableByDistance;
import teapot.builder.models.Route;
import teapot.builder.utils.interfaces.Converter;

public final class RouteConverter implements Converter<Route> {
    private TimeZone CURRENT_TIMEZONE; // TODO
    public static final RouteConverter instance = new RouteConverter(TimeZone.getDefault());
    private final CoordinatesConverter coordinatesConverter = new CoordinatesConverter();
    private final LocationConverter locationConverter = new LocationConverter();

    public RouteConverter(TimeZone CURRENT_TIMEZONE) {
        this.CURRENT_TIMEZONE = CURRENT_TIMEZONE;
    }

    @Override
    public String encode(Route route) {
        ArrayList<String> values = new ArrayList<>();
        values.add(Integer.toString(route.getId()));
        values.add(route.getName());
        values.add(coordinatesConverter.encode(route.getCoordinates()));
        values.add(route.getCreationDate().toString());
        values.add(locationConverter.encode(route.getTo()));
        values.add(locationConverter.encode(route.getFrom()));
        values.add(Long.toString(route.getDistance()));
        return String.join(";", values);
    }

    @Override
    public Route decode(String... args) {
        int i = 0;
        if (args.length == 12) {
            return new Route(                                                                       // sometimes you look at other people's code and think
                                                                                                    // why am i here? just to suffer?
                Integer.parseInt(args[i++]),
                args[i++],
                coordinatesConverter.decode(args[i++], args[i++]),
                ZonedDateTime.parse(args[i++]),
                locationConverter.decode(args[i++], args[i++], args[i++]),
                locationConverter.decode(args[i++], args[i++], args[i++]),
                Long.parseLong(args[i++]));
        }
    }

    public void setTimeZone(TimeZone timeZone) {
        CURRENT_TIMEZONE = timeZone;

    }
}
