package com.example.customer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.CompletableFuture;

public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/async71")
    public CompletableFuture<ResponseEntity<Customer>> getCustomerByIdAsync71(@PathVariable long id) {
        var customer = customerService.getCustomerById(id);
        if (customer != null) {
            return CompletableFuture.supplyAsync(() -> new ResponseEntity<>(customer, HttpStatus.OK));
        } else {
            return CompletableFuture.supplyAsync(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
    }

    @GetMapping("/async72")
    public CompletableFuture<ResponseEntity<Customer>> getCustomerByIdAsync72(@PathVariable long id) {
        var customer = customerService.getCustomerById(id);
        if (customer != null) {
            return CompletableFuture.runAsync(() -> {
                // Simulate some asynchronous operation
            }).thenApply(ignored -> new ResponseEntity<>(customer, HttpStatus.OK));
        } else {
            return CompletableFuture.runAsync(() -> {
                // Simulate some asynchronous operation
            }).thenApply(ignored -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
    }

    @GetMapping("/async73")
    public CompletableFuture<ResponseEntity<Customer>> getCustomerByIdAsync73(@PathVariable long id) {
        var customer = customerService.getCustomerById(id);
        if (customer != null) {
            return CompletableFuture.supplyAsync(() -> new ResponseEntity<>(customer, HttpStatus.OK));
        } else {
            return CompletableFuture.supplyAsync(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
    }

    @GetMapping("/async74")
    public CompletableFuture<ResponseEntity<Customer>> getCustomerByIdAsync74(@PathVariable long id) {
        var customer = customerService.getCustomerById(id);
        if (customer != null) {
            return CompletableFuture.runAsync(() -> {
                // Simulate some asynchronous operation
            }).thenApply(ignored -> new ResponseEntity<>(customer, HttpStatus.OK));
        } else {
            return CompletableFuture.runAsync(() -> {
                // Simulate some asynchronous operation
            }).thenApply(ignored -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
    }

    @GetMapping("/async75")
    public CompletableFuture<ResponseEntity<Customer>> getCustomerByIdAsync75(@PathVariable long id) {
        var customer = customerService.getCustomerById(id);
        if (customer != null) {
            return CompletableFuture.supplyAsync(() -> {
                // Simulate some asynchronous operation
                return new ResponseEntity<>(customer, HttpStatus.OK);
            });
        } else {
            return CompletableFuture.supplyAsync(() -> {
                // Simulate some asynchronous operation
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            });
        }
    }

    @GetMapping("/async76")
    public CompletableFuture<ResponseEntity<Customer>> getCustomerByIdAsync76(@PathVariable long id) {
        var customer = customerService.getCustomerById(id);
        if (customer != null) {
            return CompletableFuture.runAsync(() -> {
                // Simulate some asynchronous operation
            }).thenApply(ignored -> new ResponseEntity<>(customer, HttpStatus.OK));
        } else {
            return CompletableFuture.runAsync(() -> {
                // Simulate some asynchronous operation
            }).thenApply(ignored -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
    }

    @GetMapping("/async77")
    public CompletableFuture<ResponseEntity<Customer>> getCustomerByIdAsync77(@PathVariable long id) {
        var customer = customerService.getCustomerById(id);
        if (customer != null) {
            return CompletableFuture.supplyAsync(() -> new ResponseEntity<>(customer, HttpStatus.OK));
        } else {
            return CompletableFuture.supplyAsync(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
    }

    @GetMapping("/async78")
    public CompletableFuture<ResponseEntity<Customer>> getCustomerByIdAsync78(@PathVariable long id) {
        var customer = customerService.getCustomerById(id);
        if (customer != null) {
            return CompletableFuture.runAsync(() -> {
                // Simulate some asynchronous operation
            }).thenApply(ignored -> new ResponseEntity<>(customer, HttpStatus.OK));
        } else {
            return CompletableFuture.runAsync(() -> {
                // Simulate some asynchronous operation
            }).thenApply(ignored -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
    }
}