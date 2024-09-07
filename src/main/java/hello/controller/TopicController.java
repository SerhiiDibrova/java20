package hello.controller;

import hello.model.Topic;
import hello.service.TopicService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping("/topic")
    public List<Topic> getAllTopics() {
        return topicService.getAllTopics();
    }

    @GetMapping("/topic/{id}")
    public Topic getTopicWithID(@PathVariable String id) {
        return topicService.getTopicWithId(id);
    }

    @PostMapping("/topic")
    public void addTopic(@RequestBody Topic topic) {
        topicService.addTopic(topic);
    }

    @PutMapping("/topic/{id}")
    public void updateTopic(@PathVariable String id, @RequestBody Topic topic) {
        topicService.updateTopic(id, topic);
    }

    @DeleteMapping("/topic/{id}")
    public void deleteTopic(@PathVariable String id) {
        topicService.deleteTopic(id);
    }

    @GetMapping("/topic/minimum/length/{minLength}")
    public List<Topic> filterMinimumLengthForId(@PathVariable Integer minLength) {
        return topicService.filterMinimumLengthForId(minLength);
    }

    @GetMapping("/topic/sort")
    public List<Topic> sortTopicsWithID() {
        return topicService.sortTopicsWithID();
    }
}