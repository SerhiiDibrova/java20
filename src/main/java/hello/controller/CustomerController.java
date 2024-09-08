package com.example.customer;

import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        var customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/length/{minLength}")
    public ResponseEntity<List<Customer>> getCustomersWithMinNameLength(@PathVariable("minLength") @Min(1) int minLength) {
        var filteredCustomers = customerService.getCustomersWithMinNameLength(minLength);
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
    public ResponseEntity<String> getCustomerAsJson(@PathVariable long id) {
        var customer = customerService.getCustomerById(id);
        if (customer != null) {
            var json = """
                {
                  "id": %d,
                  "name": "%s"
                }
                """.formatted(customer.getId(), customer.getName());
            return new ResponseEntity<>(json, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/xml")
    public ResponseEntity<String> getCustomerAsXml(@PathVariable long id) {
        var customer = customerService.getCustomerById(id);
        if (customer != null) {
            var xml = """
                <?xml version="1.0" encoding="UTF-8"?>
                <customer>
                  <id>%d</id>
                  <name>%s</name>
                </customer>
                """.formatted(customer.getId(), customer.getName());
            return new ResponseEntity<>(xml, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/yaml")
    public ResponseEntity<String> getCustomerAsYaml(@PathVariable long id) {
        var customer = customerService.getCustomerById(id);
        if (customer != null) {
            var yaml = """
                id: %d
                name: "%s"
                """.formatted(customer.getId(), customer.getName());
            return new ResponseEntity<>(yaml, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public CompletableFuture<ResponseEntity<Customer>> getCustomerByIdAsync(@PathVariable long id) {
        var customer = customerService.getCustomerById(id);
        if (customer != null) {
            return CompletableFuture.completedFuture(new ResponseEntity<>(customer, HttpStatus.OK));
        } else {
            return CompletableFuture.completedFuture(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
    }
}