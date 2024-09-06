```java
package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.regex.Pattern;

public class Main {
    private List<Topic> topics = List.of(
            new Topic("1", "Java"),
            new Topic("2", "Kotlin"),
            new Topic("3", "Scala")
    );

    public String findTopics() {
        return topics.stream()
                .filter(topic -> topic.getName().contains("java"))
                .sorted((t1, t2) -> t1.getName().compareTo(t2.getName()))
                .map(Topic::getName)
                .collect(Collectors.joining(":"));
    }

    public String findTopicsWithFilter() {
        return topics.stream()
                .filter(topic -> topic.getName().contains("java"))
                .sorted((t1, t2) -> t1.getName().compareTo(t2.getName()))
                .map(Topic::getName)
                .collect(Collectors.joining(":"));
    }

    public String findIdHavingCharacter() {
        Pattern pattern = Pattern.compile(".*g.*");
        Object[] topicIdObjectList = topics.stream().map(topic -> topic.getId()).collect(Collectors.toList()).toArray();

        String[] topicIdList = Arrays.stream(topicIdObjectList).toArray(String[]::new);

        return Stream.of(topicIdList)
                .filter(pattern.asPredicate())
                .collect(Collectors.toList())
                .toString();
    }

    public String findAllFilesInPathAndSort() {
        try (Stream<Path> stream = Files.list(Paths.get(""))) {
            String joined = stream
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
        Path start = Paths.get("");
        int maxDepth = 25;
        try (Stream<Path> stream = Files.find(start, maxDepth, (path, attr) ->
                String.valueOf(path).startsWith("grad"))) {
            String joined = stream
                    .sorted()
                    .map(String::valueOf)
                    .collect(Collectors.joining("; "));
            return joined;
        } catch (IOException e) {
            return " IO exception ";
        }
    }

    public String findParticularFileInPathAndSortWithWalkFunction() {
        Path start = Paths.get("");
        int maxDepth = 5;
        try (Stream<Path> stream = Files.walk(start, maxDepth)) {
            String joined = stream
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
        Path path = Paths.get("temp.txt");
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String lines = reader
                    .lines()
                    .filter(line -> line.contains("print"))
                    .map(line -> line.substring("print".length()))
                    .collect(Collectors.joining(","));
            return lines;
        } catch (IOException e) {
            return " IO exception ";
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        System.out.println(main.findTopics());
        System.out.println(main.findTopicsWithFilter());
        System.out.println(main.findIdHavingCharacter());
        System.out.println(main.findAllFilesInPathAndSort());
        System.out.println(main.findParticularFileInPathAndSort());
        System.out.println(main.findParticularFileInPathAndSortWithWalkFunction());
        System.out.println(main.readFileWithStreamFunction());
    }
}

record Topic(String id, String name) {
}
```