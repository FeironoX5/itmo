package teapot.builder.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import teapot.builder.utils.interfaces.CSVStorable;

public final class CSVProvider {
    private CSVProvider() {
    }

    public static <T extends Collection<? extends CSVStorable>> void save(String absolutePath, T collection) {
        try (FileOutputStream outputStream = new FileOutputStream(absolutePath)) {
            outputStream.write(parseToBytes(collection));
            outputStream.flush();
            System.out.println("Changes saved");
        } catch (FileNotFoundException e) {
            System.err.println("No such file");
        } catch (IOException e) {
            System.err.println("IO error");
        }
    }

    public static <T extends CSVStorable> Object[] load(String absolutePath) {
        // output
        T[] res; // FIXME unable to make ArrayList
        // init file
        File inputFile = new File(absolutePath);
        // try with resources
        try (InputStreamReader inputReader = new InputStreamReader(
                new FileInputStream(inputFile), StandardCharsets.UTF_8)) {
            ArrayList<String> lines = parseLines(inputReader);
            lines.forEach(line -> {
                
            });
        } catch (FileNotFoundException e) {
            System.err.println("No such file");
        } catch (IOException e) {
            System.err.println("IO error");
        } catch (SecurityException e) {
            System.err.println("No access");
        }
        return res.toArray();
    }

    private static ArrayList<String> parseLines(final InputStreamReader inputReader) throws IOException {
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

        }
        return lines;
    }

    public static <T extends Collection<? extends CSVStorable>> byte[] parseToBytes(T collection) {
        return collection.stream()
                .map(CSVStorable::encode)
                .collect(Collectors.joining(",")).getBytes(StandardCharsets.UTF_8);
    }
}
