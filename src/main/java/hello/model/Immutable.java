package hello.model;

import java.util.Collections;
import java.util.List;

public final class Immutable {

    private final String status;
    private final int id;
    private final Topic topic;
    private final List<Customer> customers;

    public Immutable(int id, String status, Topic topic, List<Customer> customers) {
        this.status = status;
        this.id = id;
        this.topic = new Topic(topic.getId(), topic.getSubjectName(), topic.getSubjectDescription());
        this.customers = Collections.unmodifiableList(customers);
    }

    public String getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public Topic getTopic() {
        return topic;
    }

    public List<Customer> getCustomers() {
        return customers;
    }
}