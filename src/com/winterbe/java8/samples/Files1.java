```java
package com.winterbe.java20.samples;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Files1 {

    public static void main(String[] args) throws IOException {
        testWalk();
        testFind();
        testList();
        testLines();
        testReader();
        testWriter();
        testReadWriteLines();
        testReaderLines();
    }

    private static void testReaderLines() throws IOException {
        var path = Path.of("res/nashorn1.js");
        try (var reader = Files.newBufferedReader(path)) {
            long countPrints = reader
                    .lines()
                    .filter(line -> line.contains("print"))
                    .count();
            System.out.println(countPrints);
        }
    }

    private static void testWriter() throws IOException {
        var path = Path.of("res/output.js");
        try (var writer = Files.newBufferedWriter(path)) {
            writer.write("print('Hello World');");
        }
    }

    private static void testReader() throws IOException {
        var path = Path.of("res/nashorn1.js");
        try (var reader = Files.newBufferedReader(path)) {
            System.out.println(reader.readLine());
        }
    }

    private static void testWalk() throws IOException {
        var start = Path.of("");
        int maxDepth = 5;
        try (var stream = Files.walk(start, maxDepth)) {
            String joined = stream
                    .map(String::valueOf)
                    .filter(path -> path.endsWith(".js"))
                    .collect(Collectors.joining("; "));
            System.out.println("walk(): " + joined);
        }
    }

    private static void testFind() throws IOException {
        var start = Path.of("");
        int maxDepth = 5;
        try (var stream = Files.find(start, maxDepth, (path, attr) ->
                String.valueOf(path).endsWith(".js"))) {
            String joined = stream
                    .sorted()
                    .map(String::valueOf)
                    .collect(Collectors.joining("; "));
            System.out.println("find(): " + joined);
        }
    }

    private static void testList() throws IOException {
        try (var stream = Files.list(Path.of(""))) {
            String joined = stream
                    .map(String::valueOf)
                    .filter(path -> !path.startsWith("."))
                    .sorted()
                    .collect(Collectors.joining("; "));
            System.out.println("list(): " + joined);
        }
    }

    private static void testLines() throws IOException {
        try (var stream = Files.lines(Path.of("res/nashorn1.js"))) {
            stream
                    .filter(line -> line.contains("print"))
                    .map(String::trim)
                    .forEach(System.out::println);
        }
    }

    private static void testReadWriteLines() throws IOException {
        var lines = Files.readAllLines(Path.of("res/nashorn1.js"));
        lines.add("print('foobar');");
        Files.write(Path.of("res", "nashorn1-modified.js"), lines);
    }
}
```