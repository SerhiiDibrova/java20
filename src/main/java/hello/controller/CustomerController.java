package com.example.customer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public record CustomerController(CustomerService customerService) {

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        var customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable long id) {
        var customer = customerService.getCustomerById(id);
        if (customer != null) {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        customerService.addCustomer(customer);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable long id, @RequestBody Customer customer) {
        var existingCustomer = customerService.getCustomerById(id);
        if (existingCustomer != null) {
            customerService.updateCustomer(id, customer);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable long id) {
        var customer = customerService.getCustomerById(id);
        if (customer != null) {
            customerService.deleteCustomer(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Customer>> filterCustomersByFirstNameLength(@RequestParam int minLength) {
        var filteredCustomers = customerService.filterCustomersByFirstNameLength(minLength);
        return new ResponseEntity<>(filteredCustomers, HttpStatus.OK);
    }

    @GetMapping("/sort")
    public ResponseEntity<List<Customer>> sortCustomersById() {
        var sortedCustomers = customerService.sortCustomersById();
        return new ResponseEntity<>(sortedCustomers, HttpStatus.OK);
    }

    @GetMapping("/names")
    public ResponseEntity<String> getAllCustomerNamesJoined() {
        var names = customerService.getAllCustomerNamesJoined();
        return new ResponseEntity<>(names, HttpStatus.OK);
    }

    @GetMapping("/process")
    public ResponseEntity<List<String>> processCustomers() throws ExecutionException, InterruptedException {
        var futures = customerService.processCustomers();
        var results = futures.stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (Exception e) {
                        return "Error processing customer";
                    }
                })
                .collect(Collectors.toList());
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @GetMapping("/{id}/json")
    public ResponseEntity<String> getCustomerJson(@PathVariable long id) {
        var customer = customerService.getCustomerById(id);
        if (customer != null) {
            return new ResponseEntity<>(customer.toString(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/async")
    public CompletableFuture<ResponseEntity<Customer>> getCustomerAsync(@PathVariable long id) {
        var customer = customerService.getCustomerById(id);
        if (customer != null) {
            return CompletableFuture.completedFuture(new ResponseEntity<>(customer, HttpStatus.OK));
        } else {
            return CompletableFuture.completedFuture(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
    }
}