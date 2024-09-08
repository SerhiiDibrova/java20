```java
package hello.model;

import hello.model.UserType;

public record Customer(long id, String firstName, String lastName, UserType userType) {
    public Customer(long id, String firstName, String lastName) {
        this(id, firstName, lastName, null);
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

Note: The UserType enum or class is not provided in the original code, so it's assumed to be defined elsewhere in the project. If not, you would need to define it as well.

Also, note that I've used the `record` feature of Java 20 to create an immutable Customer class. This means that once a Customer object is created, its fields cannot be changed. If you need to modify the fields after creation, you should use a regular class instead of a record.