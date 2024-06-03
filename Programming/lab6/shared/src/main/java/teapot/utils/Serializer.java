package teapot.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.Arrays;
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
        System.err.println("!!!!! ENCODED:");
        System.err.println(gson.toJson(Objects.requireNonNull(object)));
        byte[] jsonEncodedBytes = gson.toJson(Objects.requireNonNull(object)).getBytes(StandardCharsets.UTF_8);
        System.err.println(Arrays.toString(jsonEncodedBytes));
        String jsonDecodedString = new String(jsonEncodedBytes, StandardCharsets.UTF_8);
        System.err.println(jsonDecodedString);
        Object decodedObject = gson.fromJson(jsonDecodedString, Object.class);
        System.err.println(decodedObject);
        return gson.toJson(Objects.requireNonNull(object)).getBytes(StandardCharsets.UTF_8);
    }

    public static <T> T fromBytes(byte[] bytes, Class<T> tClass) {
        try {
            System.err.println("!!!!! DECODED:");
            System.err.println(Arrays.toString(bytes));
            System.err.println(new String(bytes, StandardCharsets.UTF_8));
            return gson.fromJson(new String(bytes, StandardCharsets.UTF_8), tClass);
        } catch (IllegalStateException | JsonSyntaxException e) {
            System.err.println("Unexpected JSON format");
            e.printStackTrace();
        }
        return null;
    }
}
