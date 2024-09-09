package com.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        var immutableDataClassRecord = new ImmutableDataClassRecord("Jane", 25);
        System.out.println(immutableDataClassRecord);

        var recordExample2 = new RecordExample2("Bob", 35, "Developer");
        System.out.println(recordExample2);

        // Using switch expression with pattern matching
        String profession = "Developer";
        String result = switch (profession) {
            case "Developer" -> "Software Developer";
            case "Manager" -> "Project Manager";
            default -> "Unknown Profession";
        };
        System.out.println(result);

        // Using text blocks for multi-line strings
        String sqlQuery = """
                SELECT *
                FROM users
                WHERE age > 30
                """;
        System.out.println(sqlQuery);
    }

    public List<Path> findAllFilesInPathAndSort() {
        try (Stream<Path> stream = Files.list(Paths.get(""))) {
            return stream
                    .map(String::valueOf)
                    .filter(path -> !path.startsWith("."))
                    .sorted()
                    .map(Path::of)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String findParticularFileInPathAndSort() {
        Path start = Paths.get("");
        int maxDepth = 25;
        try (Stream<Path> stream = Files.find(start, maxDepth, (path, attr) ->
                String.valueOf(path).startsWith("grad"))) {
            return stream
                    .sorted()
                    .map(String::valueOf)
                    .collect(Collectors.joining("; "));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String findParticularFileInPathAndSortWithWalkFunction() {
        Path start = Paths.get("");
        int maxDepth = 5;
        try (Stream<Path> stream = Files.walk(start, maxDepth)) {
            return stream
                    .map(String::valueOf)
                    .filter(path -> path.startsWith("grad"))
                    .sorted()
                    .collect(Collectors.joining("; "));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String readFileWithStreamFunction() {
        Path path = Paths.get("temp.txt");
        try (var reader = Files.newBufferedReader(path)) {
            return reader
                    .lines()
                    .filter(line -> line.contains("print"))
                    .map(line -> line.substring("print".length()))
                    .collect(Collectors.joining(","));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    record RecordExample(String name, int age) {}

    record ImmutableDataClassRecord(String name, int age) {}

    record RecordExample2(String name, int age, String profession) {}
}