package hello.model;

import hello.model.UserType;
import java.util.Objects;

public record Customer(long id, String firstName, String lastName, UserType userType) {
    public Customer {
        if (id <= 0) {
            throw new IllegalArgumentException("Id must be greater than zero");
        }
        Objects.requireNonNull(firstName);
        Objects.requireNonNull(lastName);
        if (firstName.isBlank() || lastName.isBlank()) {
            throw new IllegalArgumentException("First name and last name cannot be empty");
        }
    }

    @Override
    public String toString() {
        return """
                Customer{
                    id=%d,
                    firstName='%s',
                    lastName='%s',
                    userType=%s
                }
                """.formatted(id, firstName, lastName, userType);
    }

    public void setId(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id must be greater than zero");
        }
        this.id = id;
    }

    public void setFirstName(String firstName) {
        Objects.requireNonNull(firstName);
        if (firstName.isBlank()) {
            throw new IllegalArgumentException("First name cannot be empty");
        }
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        Objects.requireNonNull(lastName);
        if (lastName.isBlank()) {
            throw new IllegalArgumentException("Last name cannot be empty");
        }
        this.lastName = lastName;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}