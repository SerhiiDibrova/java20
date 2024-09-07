package com.winterbe.java8.samples;

import java.util.Optional;

public class UserService {

    public static void main(String[] args) {
        var service = new UserService();
        
        var user = service.findUserById(101);
        
        user.ifPresent(u -> System.out.println("User found: " + u.name()));
        
        var defaultUser = user.orElse(new User(999, "Guest"));
        System.out.println("Default user: " + defaultUser.name());
        
        var name = user.map(User::name).orElse("Unknown");
        System.out.println("User's name: " + name);
    }

    public Optional<User> findUserById(int id) {
        return id == 101 ? Optional.of(new User(101, "John Doe")) : Optional.empty();
    }

    public static record User(int id, String name) {}
}