package hello.service;

import hello.model.Customer;
import hello.model.UserType;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    private List<Customer> customers = new ArrayList<>();

    @PostConstruct
    public void init() {
        var customer1 = new Customer(1, "John", "Doe");
        customer1.setUserType(UserType.ADMIN);
        customers.add(customer1);

        var customer2 = new Customer(2, "Jane", "Smith");
        customer2.setUserType(UserType.USER);
        customers.add(customer2);

        var customer3 = new Customer(3, "Bill", "Gates");
        customer3.setUserType(UserType.ALL);
        customers.add(customer3);
    }

    public List<Customer> getAllCustomers() {
        return customers;
    }

    public Customer getCustomerById(long id) {
        return customers.stream()
                .filter(c -> c.getId() == id)
                .findFirst().orElse(null);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void updateCustomer(long id, Customer customer) {
        int index = customers.indexOf(customers.stream()
                .filter(c -> c.getId() == id).findFirst().orElse(null));
        if (index != -1) {
            customers.set(index, customer);
        }
    }

    public void deleteCustomer(long id) {
        customers.removeIf(c -> c.getId() == id);
    }

    public List<Customer> filterCustomersByFirstNameLength(int minLength) {
        return customers.stream()
                .filter(c -> c.getFirstName().length() > minLength)
                .toList();
    }

    public List<Customer> sortCustomersById() {
        return customers.stream()
                .sorted((c1, c2) -> Long.compare(c1.getId(), c2.getId()))
                .toList();
    }

    public String getAllCustomerNamesJoined() {
        return customers.stream()
                .map(Customer::getFirstName)
                .collect(StringBuilder::new,
                        (sb, s) -> sb.append(s).append(", "),
                        StringBuilder::append)
                .toString().replaceAll(", $", "");
    }

    public String getUserAccessMessage(Customer customer) {
        UserType userType = customer.getUserType();
        if (userType == null) return "Unknown user type";

        return switch (userType) {
            case ADMIN -> "Welcome Admin!";
            case USER -> "Welcome User!";
            case ALL -> "Access granted to all!";
            default -> "Unknown user type";
        };
    }

    public List<String> processCustomers() {
        List<String> results = new ArrayList<>();

        for (Customer customer : customers) {
            String result = """
                    Processed customer: %s
                    """.formatted(customer.getFirstName());
            results.add(result);
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
}