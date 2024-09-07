package hello.model;

import java.util.Collections;
import java.util.List;
import jakarta.validation.constraints.NotNull;
import hello.model.Topic;
import hello.model.Customer;

public record Immutable(int id, String status, @NotNull Topic topic, List<Customer> customers) {
    public Immutable {
        Objects.requireNonNull(topic);
        var uniqueCustomers = customers == null ? List.of() : customers.stream().distinct().toList();
        this.customers = Collections.unmodifiableList(uniqueCustomers);
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