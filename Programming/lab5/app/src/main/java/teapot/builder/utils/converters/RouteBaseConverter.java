package teapot.builder.utils.converters;

import java.util.ArrayList;
import teapot.builder.models.Route;
import teapot.builder.utils.interfaces.Converter;

public final class RouteBaseConverter implements Converter<Route.Base> {
    private final CoordinatesConverter coordinatesConverter = new CoordinatesConverter();
    private final LocationConverter locationConverter = new LocationConverter();

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

    @Override
    public Route.Base decode(String... args) {
        int i = 0;
        return new Route.Base(
                args[i++],
                coordinatesConverter.decode(args[i++], args[i++]),
                locationConverter.decode(args[i++], args[i++], args[i++]),
                locationConverter.decode(args[i++], args[i++], args[i++]),
                Long.parseLong(args[i++]));
    }
}
