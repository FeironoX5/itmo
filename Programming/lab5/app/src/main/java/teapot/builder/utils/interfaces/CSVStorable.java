package teapot.builder.utils.interfaces;

import java.lang.reflect.Field;

public interface CSVStorable {
    String encode();

    static <T extends CSVStorable> T decode(
            Class<T> clazz, String... args) {
        Object res = new Object();
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) { // yes, i++
            fields[i].set(res, fields);
        }
        return (T) res;
    }
}
