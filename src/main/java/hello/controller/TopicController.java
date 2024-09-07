package hello.controller;

import hello.model.Topic;
import hello.service.TopicService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = Objects.requireNonNull(topicService);
    }

    @GetMapping("/topic")
    public List<Topic> getAllTopics() {
        return topicService.getAllTopics();
    }

    @GetMapping("/topic/{id}")
    public Topic getTopicWithID(@PathVariable String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Id cannot be empty");
        }
        return topicService.getTopicWithId(id);
    }

    @PostMapping("/topic")
    public void addTopic(@RequestBody Topic topic) {
        Objects.requireNonNull(topic, "Topic cannot be null");
        topicService.addTopic(topic);
    }

    @PutMapping("/topic/{id}")
    public void updateTopic(@PathVariable String id, @RequestBody Topic topic) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Id cannot be empty");
        }
        Objects.requireNonNull(topic, "Topic cannot be null");
        topicService.updateTopic(id, topic);
    }

    @DeleteMapping("/topic/{id}")
    public void deleteTopic(@PathVariable String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Id cannot be empty");
        }
        topicService.deleteTopic(id);
    }

    @GetMapping(value = "/topic/minimum/length/{minLength}")
    public List<Topic> filterMinimumLengthForId(@PathVariable Integer minLength) {
        if (minLength == null || minLength < 0) {
            throw new IllegalArgumentException("Min length cannot be negative");
        }
        return topicService.filterMinimumLengthForId(minLength);
    }

    @GetMapping("/topic/sort")
    public List<Topic> sortTopicsWithID() {
        return topicService.sortTopicsWithID();
    }
}