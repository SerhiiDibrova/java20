package hello.model;

import java.util.Collections;
import java.util.List;
import jakarta.validation.constraints.NotNull;
import hello.model.Topic;
import hello.model.Customer;

public final class Immutable {

    private final int id;
    private final String status;
    private final Topic topic;
    private final List<Customer> customers;

    public Immutable(int id, @NotNull String status, @NotNull Topic topic, List<Customer> customers) {
        this.id = id;
        this.status = status;
        this.topic = new Topic(topic.getId(), topic.getSubjectName(), topic.getSubjectDescription());
        this.customers = Collections.unmodifiableList(customers);
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public Topic getTopic() {
        return topic;
    }

    public List<Customer> getCustomers() {
        return customers;
    }
}