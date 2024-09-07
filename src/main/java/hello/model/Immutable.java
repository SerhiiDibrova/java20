package hello.model;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import hello.model.Topic;
import hello.model.Customer;

public record Immutable(
    int id,
    String status,
    Topic topic,
    List<Customer> customers
) {

    public Immutable(int id, String status, Topic topic, List<Customer> customers) {
        this.id = id;
        this.status = Objects.requireNonNull(status);
        this.topic = new Topic(topic.getId(), topic.getSubjectName(), topic.getSubjectDescription());
        this.customers = Collections.unmodifiableList(Objects.requireNonNull(customers));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        Immutable other = (Immutable) obj;
        return id == other.id && Objects.equals(status, other.status) 
                && topic.equals(other.topic) && customers.equals(other.customers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, topic, customers);
    }
}