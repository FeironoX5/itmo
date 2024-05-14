package teapot.builder.utils.providers;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import teapot.builder.utils.interfaces.Converter;

/**
 * Utility class for handling CSV (Comma-Separated Values) data with object
 * conversion.
 *
 * @author Gleb Kiva
 */
public class CSVProvider {

    /**
     * Saves a collection of objects to a CSV file using the specified converter.
     *
     * @param <T>          The type of objects in the collection.
     * @param absolutePath The absolute path of the CSV file to save to.
     * @param collection   The collection of objects to be saved.
     * @param converter    The converter used to encode objects into CSV format.
     */
    public static <T> void save(String absolutePath, List<T> collection, Converter<T> converter) {
        FileProvider.save(absolutePath, parseToBytes(collection, converter));
    }

    /**
     * Loads a collection of objects from a CSV file using the specified converter.
     *
     * @param <T>          The type of objects to load.
     * @param absolutePath The absolute path of the CSV file to load from.
     * @param converter    The converter used to decode CSV lines into objects.
     * @return The list of objects loaded from the CSV file.
     */
    public static <T> ArrayList<T> load(String absolutePath, Converter<T> converter) {
        ArrayList<T> res = new ArrayList<>();
        ArrayList<String> lines = FileProvider.load(absolutePath);
        lines.forEach(line -> res.add(converter.decode(line.split(";"))));
        return res;
    }

    /**
     * Converts a collection of objects into a byte array representing CSV data.
     *
     * @param <T>        The type of objects in the collection.
     * @param collection The collection of objects to convert.
     * @param converter  The converter used to encode objects into CSV format.
     * @return The byte array representing CSV data.
     */
    public static <T> byte[] parseToBytes(List<T> collection, Converter<T> converter) {
        return collection.stream()
                .map(el -> converter.encode(el))
                .collect(Collectors.joining("\n")).getBytes(StandardCharsets.UTF_8);
    }
}
