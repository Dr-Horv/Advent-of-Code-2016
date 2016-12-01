package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class FileReader {

    public static Stream<String> readFile(String path) {
        Path p = Paths.get("src/"+path);

        try {
            return Files.lines(p).map(String::trim);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println("File does not exist on path " + path + " Absolute path: " + p.toAbsolutePath().toString());
            return Stream.empty();
        }
    }

    public static Stream<String> readFileAndSplitBy(String path, String splitLinesOn) {
        return readFile(path).flatMap(s -> Arrays.stream(s.split(splitLinesOn))).map(String::trim);
    }


}
