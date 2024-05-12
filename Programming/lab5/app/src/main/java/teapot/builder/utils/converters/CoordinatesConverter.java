package teapot.builder.utils.converters;

import java.util.ArrayList;
import teapot.builder.Coordinates;
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
    public Coordinates decode(String args) {
        String[] values = args.split(";");
        return new Coordinates(
                Integer.parseInt(values[0]),
                Double.parseDouble(values[1]));
    }

}
