package hello.model;

import hello.model.UserType;
import java.util.Objects;

public class Customer {
    private final long id;
    private final String firstName;
    private final String lastName;
    private UserType userType;

    public Customer(long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = Objects.requireNonNull(firstName, "First name cannot be null");
        this.lastName = Objects.requireNonNull(lastName, "Last name cannot be null");
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userType=" + userType +
                '}';
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = Objects.requireNonNull(userType, "User type cannot be null");
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}