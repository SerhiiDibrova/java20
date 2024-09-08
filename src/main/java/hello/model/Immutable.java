package hello.model;

import java.util.Collections;
import java.util.List;
import hello.model.Topic;
import hello.model.Customer;

public record Immutable(int id, String status, Topic topic, List<Customer> customers) {

    public Immutable {
        Objects.requireNonNull(topic);
        this.customers = Collections.unmodifiableList(customers == null ? List.of() : new ArrayList<>(customers));
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