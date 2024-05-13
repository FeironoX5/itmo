package teapot.builder.utils.converters;

import java.util.ArrayList;

import teapot.builder.models.Coordinates;
import teapot.builder.utils.interfaces.Converter;

public final class CoordinatesConverter implements Converter<Coordinates> {
    @Override
    public String encode(Coordinates coordinates) {
        ArrayList<String> values = new ArrayList<>();
        values.add(Integer.toString(coordinates.getX()));
        values.add(Double.toString(coordinates.getY()));
        return String.join(";", values);

    }

    @Override
    public Coordinates decode(String... args) {
        var i = 0;
        return new Coordinates(
                Integer.parseInt(args[i++]),
                Double.parseDouble(args[i++]));
    }

}
