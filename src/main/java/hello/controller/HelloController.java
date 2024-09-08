Here is the refactored code using Java 20 features:

```java
package com.example;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class Main {

    private static final String TOPIC_ID = "Topic ID: ";
    private static final String TOPIC_NAME = "Topic Name: ";
    private static final String TOPIC_DESCRIPTION = "Topic Description: ";
    private static final String TOPIC_STATUS = "Topic Status: ";

    @Autowired
    private TopicService topicService;

    public record Topic(String id, String name, String description, String status) {
        // No need for a constructor or getters/setters in a record class.
    }

    @PostConstruct
    public void init() {
        var topics = getTopics();
        printTopicDetails(topics);
    }

    private List<Topic> getTopics() {
        return topicService.getTopics().stream()
                .map(topic -> new Topic(
                        topic.getId(),
                        topic.getName(),
                        topic.getDescription(),
                        topic.getStatus()))
                .collect(Collectors.toList());
    }

    private void printTopicDetails(List<Topic> topics) {
        for (var topic : topics) {
            System.out.println("""
                    %s%s
                    %s%s
                    %s%s
                    %s%s""".formatted(
                    TOPIC_ID, topic.id(),
                    TOPIC_NAME, topic.name(),
                    TOPIC_DESCRIPTION, topic.description(),
                    TOPIC_STATUS, topic.status()));
        }
    }

    public static void main(String[] args) {
        var main = new Main();
        main.init();
    }
}
```

Note that I have applied the following Java 20 features:

*   `var` keyword for local variable type inference.
*   `record` classes for immutable data structures.

Also, note that some parts of the original code were not refactored as they seemed to be using deprecated or removed APIs in Java 20. You may need to adjust those parts according to your specific requirements and dependencies.