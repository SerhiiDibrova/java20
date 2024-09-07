package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class CustomerService {

    private List<Customer> customers = new ArrayList<>();

    public CustomerService() {
        var customer1 = new Customer(1, "John", "Doe");
        var customer2 = new Customer(2, "Jane", "Smith");
        var customer3 = new Customer(3, "Bill", "Gates");

        customer1.setUserType(UserType.ADMIN);
        customer2.setUserType(UserType.USER);
        customer3.setUserType(UserType.ALL);

        customers.add(customer1);
        customers.add(customer2);
        customers.add(customer3);
    }

    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers);
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
        var indexOpt = IntStream.range(0, customers.size())
                .filter(i -> customers.get(i).getId() == id)
                .findFirst();
        if (indexOpt.isPresent()) {
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
        var executor = java.util.concurrent.Executors.newFixedThreadPool(3);
        var results = new ArrayList<String>();

        for (var customer : customers) {
            var result = executor.submit(() -> {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                return "Processed customer: " + customer.getFirstName();
            });
            results.add(result.get());
        }

        executor.shutdown();
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