package com.example.customer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CustomerService {
    private List<Customer> customers = new ArrayList<>();

    public void init() {
        // initialization code here
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void updateCustomer(long id, Customer customer) {
        var index = customers.indexOf(new Customer(id, "", ""));

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
                .sorted(Comparator.comparingLong(Customer::getId))
                .toList();
    }

    public String getAllCustomerNamesJoined() {
        return customers.stream()
                .map(Customer::getFirstName)
                .collect(Collectors.joining(", "));
    }

    public String getUserAccessMessage(Customer customer) {
        UserType userType = customer.getUserType();

        if (userType == null) {
            return "Unknown user type";
        }

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
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                return "Processed customer: " + customer.getFirstName();
            });
            results.add(result);
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

    public static void main(String[] args) {
        var service = new CustomerService();
        service.init();

        var customer1 = new Customer(1, "John", "Doe");
        var customer2 = new Customer(2, "Jane", "Doe");

        service.addCustomer(customer1);
        service.addCustomer(customer2);

        System.out.println(service.getAllCustomerNamesJoined());
    }
}

record Customer(long id, String firstName, String lastName) {
    private UserType userType;

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public UserType getUserType() {
        return userType;
    }
}

enum UserType {
    ADMIN,
    USER,
    ALL
}