package teapot.builder.utils.interfaces;

public interface Converter<T> {
    String encode(T object);

    T decode(String args);
}
