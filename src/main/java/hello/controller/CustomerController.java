package com.example.customer;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

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

    @GetMapping("/{id}/async")
    public CompletableFuture<ResponseEntity<Customer>> getCustomerByIdAsync(@PathVariable long id) {
        return CompletableFuture.supplyAsync(() -> {
            var customer = customerService.getCustomerById(id);
            if (customer != null) {
                return new ResponseEntity<>(customer, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        });
    }
}