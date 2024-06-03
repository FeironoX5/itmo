package teapot.utils;

import teapot.models.Response;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public final class FileProvider {


    private FileProvider() {
        // Private constructor to prevent instantiation
    }

    public static Response save(String absolutePath, byte[] data) {
        Response response = new Response();
        try (FileOutputStream outputStream = new FileOutputStream(
                RequirementHandler.requireNonEmptyString(absolutePath)
        )) {
            outputStream.write(data);
            outputStream.flush();
            response.print("Changes saved");
        } catch (FileNotFoundException e) {
            response.err("No such file");
        } catch (IOException e) {
            response.err("IO error");
        }
        return response;
    }

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
        if (!lineBuffer.isEmpty()) {
            lines.add(lineBuffer.toString());
        }
        return lines;
    }
}
