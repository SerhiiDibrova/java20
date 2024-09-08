package hello.model;

import hello.model.UserType;

public record Customer(long id, String firstName, String lastName, UserType userType) {
    public Customer {
        if (firstName == null || lastName == null) {
            throw new NullPointerException("First name and last name cannot be null");
        }
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
}