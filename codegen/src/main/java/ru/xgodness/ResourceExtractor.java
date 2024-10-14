package ru.xgodness;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class ResourceExtractor {
    public String readResource(String filename) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(filename);

        if (inputStream == null) {
            throw new IllegalArgumentException("File %s was not found".formatted(filename));
        } else {
            Scanner scanner = new Scanner(inputStream).useDelimiter("\\A");
            return scanner.hasNext() ? scanner.next() : "";
        }
    }
}
