package hello.controller;

import hello.model.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import hello.service.CustomerService;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable long id) {
        Customer customer = customerService.getCustomerById(id);
        return customer != null ? new ResponseEntity<>(customer, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        customerService.addCustomer(customer);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable long id, @RequestBody Customer customer) {
        Customer existingCustomer = customerService.getCustomerById(id);
        if (existingCustomer != null) {
            customerService.updateCustomer(id, customer);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable long id) {
        Customer customer = customerService.getCustomerById(id);
        if (customer != null) {
            customerService.deleteCustomer(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Customer>> filterCustomersByFirstNameLength(@RequestParam int minLength) {
        List<Customer> filteredCustomers = customerService.filterCustomersByFirstNameLength(minLength);
        return new ResponseEntity<>(filteredCustomers, HttpStatus.OK);
    }

    @GetMapping("/sort")
    public ResponseEntity<List<Customer>> sortCustomersById() {
        List<Customer> sortedCustomers = customerService.sortCustomersById();
        return new ResponseEntity<>(sortedCustomers, HttpStatus.OK);
    }

    @GetMapping("/names")
    public ResponseEntity<String> getAllCustomerNamesJoined() {
        String names = customerService.getAllCustomerNamesJoined();
        return new ResponseEntity<>(names, HttpStatus.OK);
    }

    @GetMapping("/process")
    public ResponseEntity<List<String>> processCustomers() throws ExecutionException, InterruptedException {
        List<Future<String>> futures = customerService.processCustomers();
        List<String> results = futures.stream()
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
        Customer customer = customerService.getCustomerById(id);
        return customer != null ? new ResponseEntity<>(customerService.getCustomerJson(customer), HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}/async")
    public CompletableFuture<ResponseEntity<Customer>> getCustomerByIdAsync(@PathVariable long id) {
        return CompletableFuture.supplyAsync(() -> {
            Customer customer = customerService.getCustomerById(id);
            return customer != null ? new ResponseEntity<>(customer, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        });
    }
}