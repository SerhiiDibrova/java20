package hello.model;

import java.util.Collections;
import java.util.List;
import jakarta.validation.constraints.NotNull;
import hello.model.Topic;
import hello.model.Customer;

public record Immutable(int id, String status, Topic topic, List<Customer> customers) {

    public Immutable {
        this.customers = Collections.unmodifiableList(customers);
    }

    public String getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public Topic getTopic() {
        return new Topic(topic.getId(), topic.getSubjectName(), topic.getSubjectDescription());
    }

    public List<Customer> getCustomers() {
        return customers;
    }
}