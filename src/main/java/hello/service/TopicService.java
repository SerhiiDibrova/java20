package hello.service;

import hello.declaration.CustomPredicate;
import hello.model.Topic;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
public class TopicService {

    private List<Topic> topics = new ArrayList<>(List.of(
            new Topic("spring", "Spring Framework", "Spring Framework Description"),
            new Topic("java", "Core Java", "Java Description"),
            new Topic("javascript", "javascript Framework", "javascript Framework Description")
    ));

    public List<Topic> getAllTopics() {
        return topics;
    }

    public Topic getTopicWithId(String id) {
        return topics.stream().filter(topic -> topic.getId().equals(id)).findFirst().orElse(null);
    }

    public void addTopic(Topic topic) {
        topics.add(topic);
    }

    public void updateTopic(String id, Topic topic) {
        IntStream.range(0, topics.size())
                .filter(index -> id.equals(topics.get(index).getId()))
                .findFirst()
                .ifPresent(index -> topics.set(index, topic));
    }

    public void deleteTopic(String id) {
        topics.removeIf(topic -> topic.getId().equals(id));
    }

    public List<Topic> filterMinimumLengthForId(Integer minLength) {
        return printTopicsWithPredicate(topics, topic -> topic.getId().length() > minLength);
    }

    private static List<Topic> printTopicsWithPredicate(List<Topic> topicList, CustomPredicate<Topic> tester) {
        List<Topic> resultTopic = new ArrayList<>();
        topicList.forEach(topic -> {
            if (tester.test(topic)) resultTopic.add(topic);
        });
        return resultTopic;
    }

    public List<Topic> sortTopicsWithID() {
        topics.sort(Comparator.comparing(Topic::getId));
        return topics;
    }

    public String returnAllTopicIDWithStringSlicing() {
        List<String> topicIds = topics.stream().map(Topic::getId).collect(Collectors.toList());
        return String.join(":", topicIds);
    }

    public String makeDistinctAndSortCharacters(String join) {
        return join.chars().distinct()
                .mapToObj(id -> String.valueOf((char) id))
                .sorted()
                .collect(Collectors.joining());
    }

    public String splitAllIdWithColonSelectIDWithJavaKeywordThenSortThenJoin(String join) {
        return Pattern.compile(":")
                .splitAsStream(join)
                .filter(s -> s.contains("java"))
                .sorted()
                .collect(Collectors.joining(":"));
    }

    public String findIdHavingCharacter() {
        Pattern pattern = Pattern.compile(".*g.*");
        return topics.stream()
                .map(Topic::getId)
                .filter(pattern.asPredicate())
                .collect(Collectors.toList())
                .toString();
    }

    public String findAllFilesInPathAndSort() {
        try (Stream<Path> stream = Files.list(Paths.get(""))) {
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
        Path start = Paths.get("");
        int maxDepth = 25;
        try (Stream<Path> stream = Files.find(start, maxDepth, (path, attr) ->
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
        Path start = Paths.get("");
        int maxDepth = 5;
        try (Stream<Path> stream = Files.walk(start, maxDepth)) {
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
        Path path = Paths.get("temp.txt");
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            return reader
                    .lines()
                    .filter(line -> line.contains("print"))
                    .map(line -> line.substring("print".length()))
                    .collect(Collectors.joining(","));
        } catch (IOException e) {
            return " IO exception ";
        }
    }
}