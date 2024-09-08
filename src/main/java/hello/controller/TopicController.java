Here is the refactored code in Java 20:

```java
package hello.controller;

import hello.model.Topic;
import hello.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TopicController {

    private final TopicService topicService;

    @Autowired
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    /**
     * Get all Topic
     * @return
     */
    @GetMapping("/topic")
    public List<Topic> getAllTopics() {
        return topicService.getAllTopics();
    }

    /**
     * get Topic with ID
     * @param id
     * @return
     */
    @GetMapping("/topic/{id}")
    public Topic getTopicWithID(@PathVariable String id) {
        return topicService.getTopicWithId(id);
    }

    /**
     * Add a new topic in list
     * @param topic
     */
    @PostMapping("/topic")
    public void addTopic(@RequestBody Topic topic) {
        topicService.addTopic(topic);
    }

    /**
     * Update Topic in List with id
     * @param id
     * @param topic
     */
    @PutMapping("/topic/{id}")
    public void updateTopic(@PathVariable String id, @RequestBody Topic topic) {
        topicService.updateTopic(id, topic);
    }


    /**
     * Delete a topic with ID
     * @param id
     */
    @DeleteMapping("/topic/{id}")
    public void deleteTopic(@PathVariable String id) {
        topicService.deleteTopic(id);
    }

    /**
     * Get all topics with Id length greater then minimum length
     * @param minLength
     * @return
     */
    @GetMapping("/topic/minimum/length/{minLength}")
    public List<Topic> filterMinimumLengthForId(@PathVariable Integer minLength) {
        return topicService.filterMinimumLengthForId(minLength);
    }


    /**
     * Sort with Id
     * @return
     */
    @GetMapping("/topic/sort")
    public List<Topic> sortTopicsWithID() {
        return topicService.sortTopicsWithID();
    }
}
```

Note that I have assumed the `TopicService` class is already updated to be compatible with Java 20 and Spring latest version. If it's not, you would need to update it accordingly.

Also, please ensure that all dependencies are updated to the latest compatible versions with Java 20.