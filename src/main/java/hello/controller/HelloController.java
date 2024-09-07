package com.example.topic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TopicServiceImpl implements TopicService {

    @Override
    public List<Topic> join(List<Topic> topics) {
        return topics.stream()
                .map(Topic::name)
                .collect(Collectors.joining(","))
                .chars()
                .distinct()
                .sorted()
                .mapToObj(c -> new Topic(String.valueOf((char) c)))
                .toList();
    }

    @Override
    public List<Topic> distinct(List<Topic> topics) {
        return topics.stream()
                .distinct()
                .toList();
    }

    @Override
    public List<Topic> split(List<Topic> topics) {
        return topics.stream()
                .map(Topic::name)
                .flatMap(s -> Stream.of(s.split("-")))
                .filter(s -> s.contains("Java"))
                .sorted()
                .distinct()
                .toList()
                .stream()
                .map(Topic::new)
                .toList();
    }

    @Override
    public List<Topic> find(List<Topic> topics, char character) {
        return topics.stream()
                .filter(topic -> topic.name().indexOf(character) != -1)
                .toList();
    }

    @Override
    public String findAllFiles() throws IOException {
        try (var files = Files.list(Path.of("."))) {
            return files.map(Object::toString).sorted().collect(Collectors.joining(","));
        }
    }

    @Override
    public String findParticularFile(String fileName) throws IOException {
        try (var files = Files.walk(Path.of(".")).filter(Files::isRegularFile)) {
            var file = files.filter(file -> file.getFileName().toString().startsWith(fileName))
                    .findFirst()
                    .orElseThrow();
            return Files.readString(file);
        }
    }

    @Override
    public String findParticularFilesWithWalkFunction(String fileName) throws IOException {
        try (var files = Files.walk(Path.of(".")).filter(Files::isRegularFile)) {
            var fileContent = files.filter(file -> file.getFileName().toString().startsWith(fileName))
                    .map(Files::readString)
                    .collect(Collectors.joining(","));
            return """
                ----------Find File In Present Directory Which Starts With "%s", Provided Maximum Depth=25 And Sort :  with walk function
                %s""".formatted(fileName, fileContent);
        }
    }

    @Override
    public String readFile(String fileName, String keyword) throws IOException {
        try (var lines = Files.lines(Path.of(fileName))) {
            return lines.filter(line -> line.contains(keyword))
                    .collect(Collectors.joining(","));
        }
    }
}