package teapot.builder.utils.converters;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.TimeZone;

import teapot.builder.Route;
import teapot.builder.utils.interfaces.Converter;

public class RouteConverter implements Converter<Route> {
    private final TimeZone CURRENT_TIMEZONE;
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
        values.add(coordinatesConverter.encode(route.getCoordinates()));// FIXME replace with coordinates converter
        values.add(new SimpleDateFormat().format(route.getCreationDate()));
        values.add(locationConverter.encode(route.getTo()));
        values.add(locationConverter.encode(route.getFrom()));
        values.add(Long.toString(route.getDistance()));
        return String.join(";", values);
    }

    @Override
    public Route decode(String args) {
        String[] values = args.split(";");
        return new Route(
                Integer.parseInt(values[0]),
                values[1],
                coordinatesConverter.decode(String.join(values[2], values[3])),
                ZonedDateTime.parse(values[4]), // TODO zone tine parsing
                locationConverter.decode(String.join(values[5], values[6], values[7])),
                locationConverter.decode(String.join(values[8], values[9], values[19])),
                Long.parseLong(values[11]));
    }

}
