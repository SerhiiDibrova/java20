package com.winterbe.java8.samples;

import java.util.Optional;

public class UserService {

    public static void main(String[] args) {
        var service = new UserService();
        
        var user = service.findUserById(101);
        
        // Check if user is present and print details
        user.ifPresent(u -> System.out.println("User found: " + u.name()));
        
        // OrElse example
        var defaultUser = user.orElse(new User(999, "Guest"));
        System.out.println("Default user: " + defaultUser.name());
        
        // Map example
        var name = user.map(User::name).orElse("Unknown");
        System.out.println("User's name: " + name);
    }

    // Example method using Optional
    public Optional<User> findUserById(int id) {
        if (id == 101) {
            return Optional.of(new User(101, "John Doe"));
        }
        return Optional.empty();
    }

    public static record User(int id, String name) {}
}