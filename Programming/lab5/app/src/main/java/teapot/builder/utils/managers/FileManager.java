package teapot.builder.utils.managers;
// package teapot.builder.utils;

// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStream;
// import java.io.InputStreamReader;
// import java.io.LineNumberReader;

// public class FileManager {
//     public void loadResource(String resourceName) throws IllegalArgumentException, IOException {
//         InputStream inputStream = ClassLoader.getSystemResourceAsStream(resourceName);
//         if (inputStream == null) {
//             throw new IllegalArgumentException(String.format("Resource %s not exists", resourceName));
//         }
//         InputStreamReader streamReader = new InputStreamReader(inputStream);
//         BufferedReader reader = new LineNumberReader(streamReader);
//         String line;
//         while ((line = reader.readLine()) != null) {
//             System.out.println(line);
//         }
//     }
// }
