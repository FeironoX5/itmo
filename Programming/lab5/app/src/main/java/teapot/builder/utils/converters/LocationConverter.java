package teapot.builder.utils.converters;

import java.util.ArrayList;

import teapot.builder.models.Location;
import teapot.builder.utils.interfaces.Converter;

public final class LocationConverter implements Converter<Location> {
    @Override
    public String encode(Location coordinates) {
        ArrayList<String> values = new ArrayList<>();
        values.add(Long.toString(coordinates.getX()));
        values.add(Float.toString(coordinates.getY()));
        values.add(coordinates.getName());
        return String.join(";", values);
    }

    @Override
    public Location decode(String... args) {
        var i = 0;
        return new Location(
                Long.parseLong(args[i++]),
                Float.parseFloat(args[i++]),
                args[i++]);
    }
}
