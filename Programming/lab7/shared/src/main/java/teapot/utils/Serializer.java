package teapot.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

public class Serializer {
    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(ZonedDateTime.class, new TypeAdapter<ZonedDateTime>() {
                @Override
                public void write(JsonWriter out, ZonedDateTime value) throws IOException {
                    out.value(value.toString());
                }

                @Override
                public ZonedDateTime read(JsonReader in) throws IOException {
                    return ZonedDateTime.parse(in.nextString());
                }
            })
            .enableComplexMapKeySerialization()
            .serializeNulls()
            .disableHtmlEscaping()
            .create();

    public static byte[] toBytes(Object object) {
//        System.err.println("!!!!! ENCODED:");
//        System.err.println(gson.toJson(Objects.requireNonNull(object)));
//        byte[] jsonEncodedBytes = gson.toJson(Objects.requireNonNull(object)).getBytes(StandardCharsets.UTF_8);
//        System.err.println(Arrays.toString(jsonEncodedBytes));
//        String jsonDecodedString = new String(jsonEncodedBytes, StandardCharsets.UTF_8);
//        System.err.println(jsonDecodedString);
//        Object decodedObject = gson.fromJson(jsonDecodedString, Object.class);
//        System.err.println(decodedObject);
        return gson.toJson(Objects.requireNonNull(object)).getBytes(StandardCharsets.UTF_8);
    }

    public static <T> T fromBytes(byte[] bytes, Class<T> tClass) throws IllegalArgumentException {
        return fromString(new String(bytes, StandardCharsets.UTF_8), tClass);
    }

    public static <T> T fromString(String s, Class<T> tClass) throws IllegalArgumentException {
        try {
            return gson.fromJson(s, tClass);
        } catch (IllegalStateException | JsonSyntaxException e) {
            throw new IllegalArgumentException("Unexpected JSON format");
        }
    }

    public static <T> List<T> fromStringAsCollection(String s, Class<T> tClass) throws IllegalArgumentException {
        try {
            TypeToken<?> type = TypeToken.getParameterized(List.class, tClass);
            return (List<T>) gson.fromJson(s, type);
        } catch (IllegalStateException | JsonSyntaxException e) {
            throw new IllegalArgumentException("Unexpected JSON format");
        }
    }
}
