package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    private record Topic(String id, String name) {}

    private List<Topic> topics = List.of(
            new Topic("java", "Java"),
            new Topic("python", "Python"),
            new Topic("csharp", "C#")
    );

    public String findIdHavingCharacter() {
        var pattern = Pattern.compile(".*g.*");
        return topics.stream()
                .map(Topic::id)
                .filter(pattern.asPredicate())
                .collect(Collectors.toList())
                .toString();
    }

    public String findAllFilesInPathAndSort() {
        try (var stream = Files.list(Paths.get(""))) {
            return stream
                    .map(String::valueOf)
                    .filter(path -> !path.startsWith("."))
                    .sorted()
                    .collect(Collectors.joining("; "));
        } catch (IOException e) {
            return " Error in IO";
        }
    }

    public String findParticularFileInPathAndSort() {
        var start = Paths.get("");
        int maxDepth = 25;
        try (var stream = Files.find(start, maxDepth, (path, attr) ->
                String.valueOf(path).startsWith("grad"))) {
            return stream
                    .sorted()
                    .map(String::valueOf)
                    .collect(Collectors.joining("; "));
        } catch (IOException e) {
            return " IO exception ";
        }
    }

    public String findParticularFileInPathAndSortWithWalkFunction() {
        var start = Paths.get("");
        int maxDepth = 5;
        try (var stream = Files.walk(start, maxDepth)) {
            return stream
                    .map(String::valueOf)
                    .filter(path -> path.startsWith("grad"))
                    .sorted()
                    .collect(Collectors.joining("; "));
        } catch (IOException e) {
            return " IO exception ";
        }
    }

    public String readFileWithStreamFunction() {
        var path = Paths.get("temp.txt");
        try (var reader = Files.newBufferedReader(path)) {
            return reader
                    .lines()
                    .filter(line -> line.contains("print"))
                    .map(line -> line.substring("print".length()))
                    .collect(Collectors.joining(","));
        } catch (IOException e) {
            return " IO exception ";
        }
    }

    public static void main(String[] args) {
        var main = new Main();
        System.out.println(main.findIdHavingCharacter());
        System.out.println(main.findAllFilesInPathAndSort());
        System.out.println(main.findParticularFileInPathAndSort());
        System.out.println(main.findParticularFileInPathAndSortWithWalkFunction());
        System.out.println(main.readFileWithStreamFunction());
    }
}