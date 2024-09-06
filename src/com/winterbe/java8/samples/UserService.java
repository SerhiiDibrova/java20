package com.winterbe.java8.samples;

import java.util.Optional;

public class UserService {

    public static void main(String[] args) {
        UserService service = new UserService();
        
        Optional<User> user = service.findUserById(101);
        
        // Check if user is present and print details
        user.ifPresentOrElse(u -> System.out.println("User found: " + u.getName()), () -> System.out.println("No user found"));
        
        // OrElse example
        User defaultUser = user.orElseGet(() -> new User(999, "Guest"));
        System.out.println("Default user: " + defaultUser.getName());
        
        // Map example
        String name = user.map(User::getName).orElseGet(() -> "Unknown");
        System.out.println("User's name: " + name);
    }

    // Example method using Optional
    public Optional<User> findUserById(int id) {
        if (id == 101) {
            return Optional.of(new User(id, "John Doe"));
        }
        return Optional.empty();
    }

    public static class User {
        private int id;
        private String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
}