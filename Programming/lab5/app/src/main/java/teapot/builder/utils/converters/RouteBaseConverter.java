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
        // System.err.println("XXXXXXXXXX");
        // for (var c: args) {
        //     System.err.println(c);
        // }
        // for (char ch : args[9].toCharArray()) {
        //     System.err.print((int) ch);
        //     System.err.println("!");
        // }
        // System.err.println("\" 987654321" + args[9] + "0123456789\"");
        return new Route.Base(
                args[i++],
                coordinatesConverter.decode(args[i++], args[i++]),
                locationConverter.decode(args[i++], args[i++], args[i++]),
                locationConverter.decode(args[i++], args[i++], args[i++]),
                Long.parseLong(args[i++]));
    }
}
