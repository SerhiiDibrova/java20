package hello.model;

import java.util.Collections;
import java.util.List;
import hello.model.Topic;
import hello.model.Customer;

public record Immutable(int id, String status, Topic topic, List<Customer> customers) {

    public Immutable {
        Objects.requireNonNull(topic, "Topic cannot be null");
        if (customers == null || customers.isEmpty()) {
            throw new IllegalArgumentException("Customers list cannot be empty or null");
        }
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