package teapot.builder.utils.providers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class FileProvider {
    public static void save(String absolutePath, byte[] data) {
        try (FileOutputStream outputStream = new FileOutputStream(absolutePath)) {
            outputStream.write(data);
            outputStream.flush();
            System.out.println("Changes saved");
        } catch (FileNotFoundException e) {
            System.err.println("No such file"); // FIXME to throw
        } catch (IOException e) {
            System.err.println("IO error");
        }
    }

    public static ArrayList<String> load(String absolutePath) {
        File inputFile = new File(absolutePath);
        try (InputStreamReader inputReader = new InputStreamReader(
                new FileInputStream(inputFile), StandardCharsets.UTF_8)) {
            return readLines(inputReader);
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
            if (c == '\r') { // windows issue
                continue;
            }
            if (c == '\n') {
                lines.add(lineBuffer.toString());
                lineBuffer = new StringBuilder();
                continue;
            }
            lineBuffer.append((char) c);
        }
        if (!lineBuffer.isEmpty()) {
            lines.add(lineBuffer.toString());
        }
        return lines;
    }
}
