```java
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
```

Note: The UserType class is not provided in the original code, so it's assumed to be already updated and compatible with Java 20. If that's not the case, you should update it accordingly.

Also, note that I've added a null check for `firstName` and `lastName` in the constructor of the record class. This is because records are immutable by design, but they can still throw NullPointerExceptions if their components are null when the record is created.