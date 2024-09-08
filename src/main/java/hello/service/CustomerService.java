package hello.service;

import hello.model.Customer;
import hello.model.UserType;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private List<Customer> customers = new ArrayList<>();

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public OptionalInt findCustomerIndexById(long id) {
        return customers.indexOf(customer -> customer.getId() == id);
    }

    public void updateCustomer(long id, Customer customer) {
        var indexOpt = findCustomerIndexById(id);
        if (indexOpt.isPresent() && indexOpt.getAsInt() != -1) {
            customers.set(indexOpt.getAsInt(), customer);
        }
    }

    public void deleteCustomer(long id) {
        customers.removeIf(c -> c.getId() == id);
    }

    public List<Customer> filterCustomersByFirstNameLength(int minLength) {
        return customers.stream()
                .filter(c -> c.getFirstName().length() > minLength)
                .collect(Collectors.toList());
    }

    public List<Customer> sortCustomersById() {
        return customers.stream()
                .sorted((c1, c2) -> Long.compare(c1.getId(), c2.getId()))
                .collect(Collectors.toList());
    }

    public String getAllCustomerNamesJoined() {
        return customers.stream()
                .map(Customer::getFirstName)
                .collect(Collectors.joining(", "));
    }

    public String getUserAccessMessage(Customer customer) {
        var userType = customer.getUserType();
        if (userType == null) return "Unknown user type";

        return switch (userType) {
            case ADMIN -> "Welcome Admin!";
            case USER -> "Welcome User!";
            case ALL -> "Access granted to all!";
            default -> "Unknown user type";
        };
    }

    public List<String> processCustomers() {
        var results = new ArrayList<String>();

        for (var customer : customers) {
            try {
                Thread.sleep(100);
                results.add("Processed customer: " + customer.getFirstName());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        return results;
    }

    public String getCustomerJson(Customer customer) {
        return """
                {
                  "id": %d,
                  "firstName": "%s",
                  "lastName": "%s",
                  "userType": "%s"
                }
                """.formatted(customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getUserType());
    }

    @PostConstruct
    public void init() {
        customers.add(new Customer(1, "John", "Doe"));
        customers.add(new Customer(2, "Jane", "Doe"));

        customers.forEach(c -> c.setUserType(UserType.USER));
    }
}

enum UserType {
    ADMIN,
    USER,
    ALL
}

class Customer {
    private long id;
    private String firstName;
    private String lastName;
    private UserType userType;

    public Customer(long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public UserType getUserType() {
        return userType;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }
}