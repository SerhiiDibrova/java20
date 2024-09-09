package hello.model;

import java.util.List;
import hello.model.Topic;
import hello.model.Customer;

public record Immutable(int id, String status, Topic topic, List<Customer> customers) {
    public Immutable {
        this.customers = Collections.unmodifiableList(customers);
        this.topic = new Topic(topic.id(), topic.subjectName(), topic.subjectDescription());
    }
}