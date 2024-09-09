package hello.service;

import hello.model.Customer;
import hello.model.UserType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class CustomerService {

    private final List<Customer> customers = new ArrayList<>();

    public CustomerService() {
        customers.add(new Customer(1, "John", "Doe"));
        customers.add(new Customer(2, "Jane", "Smith"));
        customers.add(new Customer(3, "Bill", "Gates"));
        customers.get(0).setUserType(UserType.ADMIN);
        customers.get(1).setUserType(UserType.USER);
        customers.get(2).setUserType(UserType.ALL);
    }

    public List<Customer> getAllCustomers() {
        return customers;
    }

    public Customer getCustomerById(long id) {
        return customers.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void updateCustomer(long id, Customer customer) {
        OptionalInt indexOpt = IntStream.range(0, customers.size())
                .filter(i -> customers.get(i).getId() == id)
                .findFirst();
        indexOpt.ifPresent(index -> customers.set(index, customer));
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
                .sorted(Comparator.comparingLong(Customer::getId))
                .collect(Collectors.toList());
    }

    public String getAllCustomerNamesJoined() {
        return customers.stream()
                .map(Customer::getFirstName)
                .collect(Collectors.joining(", "));
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

    public List<Future<String>> processCustomers() {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<Future<String>> results = new ArrayList<>();

        for (Customer customer : customers) {
            Future<String> result = executor.submit(() -> {
                Thread.sleep(100);
                return "Processed customer: " + customer.getFirstName();
            });
            results.add(result);
        }

        executor.shutdown();
        return results;
    }

    public String getCustomerJson(Customer customer) {
        return String.format("""
                {
                 "id": %d,
                 "firstName": "%s",
                 "lastName": "%s",
                 "userType": "%s"
                }
                """, customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getUserType());
    }
}