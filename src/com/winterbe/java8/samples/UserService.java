package com.winterbe.java20.samples;

import java.util.Optional;

public class UserService {

    public static void main(String[] args) {
        var service = new UserService();
        
        var user = service.findUserById(101);
        
        user.ifPresent(u -> System.out.println("""
                User found: 
                ID: %d
                Name: %s""".formatted(u.id(), u.name())));
        
        // OrElse example
        var defaultUser = user.orElse(new User(999, "Guest"));
        System.out.println("Default user: " + defaultUser.name());
        
        // Map example
        var name = user.map(User::name).orElse("Unknown");
        System.out.println("User's name: " + name);
    }

    // Example method using Optional
    public Optional<User> findUserById(int id) {
        return switch (id) {
            case 101 -> Optional.of(new User(id, "John Doe"));
            default -> Optional.empty();
        };
    }

    public static record User(int id, String name) {}
}