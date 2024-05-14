package teapot.builder.utils.converters;

import java.util.ArrayList;
import java.util.TimeZone;
import java.util.Arrays;
import java.time.ZonedDateTime;

import teapot.builder.models.Route;
import teapot.builder.utils.interfaces.Converter;

public final class RouteConverter implements Converter<Route> {
    private TimeZone CURRENT_TIMEZONE; // TODO
    public static final RouteConverter instance = new RouteConverter(TimeZone.getDefault());
    public final RouteBaseConverter routeBaseConverter = new RouteBaseConverter();

    public RouteConverter(TimeZone CURRENT_TIMEZONE) {
        this.CURRENT_TIMEZONE = CURRENT_TIMEZONE;
    }

    @Override
    public String encode(Route route) {
        ArrayList<String> values = new ArrayList<>();
        values.add(Integer.toString(route.getId()));
        values.add(route.getCreationDate().toString());
        values.add(routeBaseConverter.encode(route.getBase()));
        return String.join(";", values);
    }

    @Override
    public Route decode(String... args) {
        int i = 0;
        return new Route(
                Integer.parseInt(args[i++]),
                ZonedDateTime.parse(args[i++]),
                routeBaseConverter.decode(Arrays.copyOfRange(args, 2, args.length)));
    }

    public void setTimeZone(TimeZone timeZone) {
        CURRENT_TIMEZONE = timeZone;
    }

    public TimeZone getTimeZone() {
        return CURRENT_TIMEZONE;
    }
}
