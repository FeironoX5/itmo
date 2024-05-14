package teapot.builder.utils.providers;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import teapot.builder.utils.interfaces.Converter;

public class CSVProvider { // TODO class modifiers?
    public static <T> void save(String absolutePath, List<T> collection, Converter<T> converter) {
        FileProvider.save(absolutePath, parseToBytes(collection, converter));
    }

    public static <T> ArrayList<T> load(String absolutePath, Converter<T> converter) {
        ArrayList<T> res = new ArrayList<>();
        ArrayList<String> lines = FileProvider.load(absolutePath);
        lines.forEach(line -> res.add(converter.decode(line.split(";"))));
        return res;
    }

    public static <T> byte[] parseToBytes(List<T> collection, Converter<T> converter) {
        return collection.stream()
                .map(el -> converter.encode(el))
                .collect(Collectors.joining("\n")).getBytes(StandardCharsets.UTF_8);
    }
}
