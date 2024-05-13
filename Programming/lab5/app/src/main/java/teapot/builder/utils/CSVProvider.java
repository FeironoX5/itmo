package teapot.builder.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import teapot.builder.utils.interfaces.Converter;

public class CSVProvider { // TODO class modifiers?
    public static <T> void save(String absolutePath, List<T> collection, Converter<T> converter) {
        try (FileOutputStream outputStream = new FileOutputStream(absolutePath)) {
            outputStream.write(parseToBytes(collection, converter));
            outputStream.flush();
            System.out.println("Changes saved");
        } catch (FileNotFoundException e) {
            System.err.println("No such file");
        } catch (IOException e) {
            System.err.println("IO error");
        }
    }

    public static <T> ArrayList<T> load(String absolutePath, Converter<T> converter) {
        // output
        ArrayList<T> res = new ArrayList<>();
        // init file
        File inputFile = new File(absolutePath);
        // try with resources
        try (InputStreamReader inputReader = new InputStreamReader(
                new FileInputStream(inputFile), StandardCharsets.UTF_8)) {
            ArrayList<String> lines = readLines(inputReader);
            lines.forEach(line -> res.add(converter.decode(line)));
            return res;
        } catch (FileNotFoundException e) { // TODO reconsider exceptions
            throw new IllegalArgumentException("No such file");
        } catch (IOException e) {
            throw new IllegalArgumentException("IO exception");
        } catch (SecurityException e) {
            throw new IllegalArgumentException("No access granted");
        }
    }

    private static ArrayList<String> readLines(final InputStreamReader inputReader)
            throws IOException {
        ArrayList<String> lines = new ArrayList<>();
        StringBuilder lineBuffer = new StringBuilder();
        int c;
        while ((c = inputReader.read()) != -1) {
            if (c == '\n') {
                lines.add(lineBuffer.toString());
                lineBuffer = new StringBuilder();
                continue;
            }
            lineBuffer.append(c);
        }
        if (!lineBuffer.isEmpty()) {
            lines.add(lineBuffer.toString());
        }
        return lines;
    }

    public static <T> byte[] parseToBytes(List<T> collection, Converter<T> converter) {
        return collection.stream()
                .map(el -> converter.encode(el))
                .collect(Collectors.joining("")).getBytes(StandardCharsets.UTF_8);
    }
}
