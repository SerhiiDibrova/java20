package com.winterbe.java20.samples;

import java.util.Optional;

public class UserService {

    public static void main(String[] args) {
        var service = new UserService();
        
        var user = service.findUserById(101);
        
        user.ifPresent(u -> System.out.println("User found: " + u.getName()));
        
        // OrElse example
        var defaultUser = user.orElse(new User(999, "Guest"));
        System.out.println("Default user: " + defaultUser.getName());
        
        // Map example
        var name = user.map(User::getName).orElse("Unknown");
        System.out.println("User's name: " + name);
    }

    // Example method using Optional
    public Optional<User> findUserById(int id) {
        if (id == 101) {
            return Optional.of(new User(id, "John Doe"));
        }
        return Optional.empty();
    }

    public static record User(int id, String name) {}
}