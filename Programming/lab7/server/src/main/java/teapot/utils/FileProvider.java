package teapot.utils;

import teapot.ServerRunner;
import teapot.models.Response;
import teapot.utils.handlers.RequirementHandler;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public final class FileProvider {
    private FileProvider() {
        // Private constructor to prevent instantiation
    }

    public static Response save(String absolutePath, byte[] data) {
        ServerRunner.logger.info(String.format("Saving %s bytes to %s", data.length, absolutePath));
        Response response = new Response();
        try (FileOutputStream outputStream = new FileOutputStream(
                RequirementHandler.requireNonEmptyString(absolutePath)
        )) {
            outputStream.write(data);
            outputStream.flush();
            response.print("Changes saved");
            ServerRunner.logger.info(String.format("%s bytes saved to %s", data.length, absolutePath));
        } catch (FileNotFoundException e) {
            String message = String.format("File %s not found", absolutePath);
            ServerRunner.logger.error(message);
            throw new IllegalArgumentException(message);
        } catch (IOException e) {
            String message = String.format("IO exception while parsing file %s", absolutePath);
            ServerRunner.logger.error(message);
            throw new IllegalArgumentException(message);
        }
        return response;
    }

    public static ArrayList<String> load(String absolutePath) {
//        ServerRunner.logger.info(String.format("Loading file from %s", absolutePath));
        File inputFile = new File(absolutePath);
        try (InputStreamReader inputReader = new InputStreamReader(
                new FileInputStream(inputFile), StandardCharsets.UTF_8)) {
            return readLines(inputReader);
        } catch (FileNotFoundException e) {
            String message = String.format("File %s not found", absolutePath);
            ServerRunner.logger.error(message);
            throw new IllegalArgumentException(message);
        } catch (IOException e) {
            String message = String.format("IO exception while parsing file %s", absolutePath);
            ServerRunner.logger.error(message);
            throw new IllegalArgumentException(message);
        } catch (SecurityException e) {
            String message = String.format("Access error while parsing file %s", absolutePath);
            ServerRunner.logger.error(message);
            throw new IllegalArgumentException(message);
        }
    }

    public static String loadAsSingleLine(String absolutePath) {
        return String.join("", load(absolutePath));
    }

    private static ArrayList<String> readLines(final InputStreamReader inputReader)
            throws IOException {
//        ServerRunner.logger.info(String.format("Reading lines in %s", inputReader.getEncoding()));
        ArrayList<String> lines = new ArrayList<>();
        StringBuilder lineBuffer = new StringBuilder();
        int c;
        while ((c = inputReader.read()) != -1) {
            if (c == '\r') { // Windows line ending
                continue;
            }
            if (c == '\n') {
                lines.add(lineBuffer.toString());
                ServerRunner.logger.info(String.format("Line of length %s added to lines, now contains %s lines", lineBuffer.length(), lines.size()));
                lineBuffer = new StringBuilder();
                continue;
            }
            lineBuffer.append((char) c);
        }
        if (!lineBuffer.isEmpty()) {
            lines.add(lineBuffer.toString());
            ServerRunner.logger.info(String.format("Line of length %s added to lines, now contains %s lines", lineBuffer.length(), lines.size()));
        }
        ServerRunner.logger.info(String.format("%s lines loaded", lines.size()));
        return lines;
    }
}
