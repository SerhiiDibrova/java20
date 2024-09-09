package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public String findParticularTopicId(String topicIdObjectList) {
        var pattern = Pattern.compile(".*\\d{4}.*");

        var list = Arrays.asList(topicIdObjectList.split(","));
        return Stream.of(list)
                .filter(pattern.asPredicate())
                .collect(Collectors.toList())
                .toString();
    }

    public String findAllFilesInPathAndSort() {
        try (var stream = Files.list(Paths.get(""))) {
            var joined = stream
                    .map(String::valueOf)
                    .filter(path -> !path.startsWith("."))
                    .sorted()
                    .collect(Collectors.joining("; "));
            return joined;
        } catch (IOException e) {
            return " Error in IO";
        }
    }

    public String findParticularFileInPathAndSort() {
        var start = Paths.get("");
        int maxDepth = 25;
        try (var stream = Files.find(start, maxDepth, (path, attr) ->
                String.valueOf(path).startsWith("grad"))) {
            var joined = stream
                    .sorted()
                    .map(String::valueOf)
                    .collect(Collectors.joining("; "));
            return joined;
        } catch (IOException e) {
            return " IO exception ";
        }
    }

    public String findParticularFileInPathAndSortWithWalkFunction() {
        var start = Paths.get("");
        int maxDepth = 5;
        try (var stream = Files.walk(start, maxDepth)) {
            var joined = stream
                    .map(String::valueOf)
                    .filter(path -> path.startsWith("grad"))
                    .sorted()
                    .collect(Collectors.joining("; "));
            return joined;
        } catch (IOException e) {
            return " IO exception ";
        }
    }

    public String readFileWithStreamFunction() {
        var path = Paths.get("temp.txt");
        try (var reader = Files.newBufferedReader(path)) {
            var lines = reader
                    .lines()
                    .filter(line -> line.contains("print"))
                    .map(line -> line.substring("print".length()))
                    .collect(Collectors.joining(","));
            return lines;
        } catch (IOException e) {
            return " IO exception ";
        }
    }

}