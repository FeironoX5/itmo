package teapot.builder.utils.providers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Utility class for saving and loading data from files.
 *
 * @author Gleb Kiva
 */
public class FileProvider {

    /**
     * Saves binary data to a specified file.
     *
     * @param absolutePath The absolute path of the file to save to.
     * @param data         The binary data to be saved.
     */
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

    /**
     * Loads lines of text from a specified file.
     *
     * @param absolutePath The absolute path of the file to load from.
     * @return A list of strings representing lines of text from the file.
     * @throws IllegalArgumentException If the file does not exist or cannot be
     *                                  accessed.
     */
    public static ArrayList<String> load(String absolutePath) {
        File inputFile = new File(absolutePath);
        try (InputStreamReader inputReader = new InputStreamReader(
                new FileInputStream(inputFile), StandardCharsets.UTF_8)) {
            return readLines(inputReader);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("No such file");
        } catch (IOException e) {
            throw new IllegalArgumentException("IO exception");
        } catch (SecurityException e) {
            throw new IllegalArgumentException("No access granted");
        }
    }

    /**
     * Reads lines of text from an InputStreamReader.
     *
     * @param inputReader The InputStreamReader to read from.
     * @return A list of strings representing lines of text read from the reader.
     * @throws IOException If an I/O error occurs while reading from the reader.
     */
    private static ArrayList<String> readLines(final InputStreamReader inputReader)
            throws IOException {
        ArrayList<String> lines = new ArrayList<>();
        StringBuilder lineBuffer = new StringBuilder();
        int c;
        while ((c = inputReader.read()) != -1) {
            if (c == '\r') { // Windows line ending
                continue;
            }
            if (c == '\n') {
                lines.add(lineBuffer.toString());
                lineBuffer = new StringBuilder();
                continue;
            }
            lineBuffer.append((char) c);
        }
        if (lineBuffer.length() > 0) {
            lines.add(lineBuffer.toString());
        }
        return lines;
    }
}
