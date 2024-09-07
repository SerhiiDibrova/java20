package com.winterbe.java8.samples;

import java.util.Optional;

public class UserService {

    public static void main(String[] args) {
        var service = new UserService();
        
        var user = service.findUserById(101);
        
        user.ifPresent(u -> System.out.println("User found: " + u));
        
        var defaultUser = user.orElse(new User(999, "Guest"));
        System.out.println("Default user: " + defaultUser);
        
        var name = user.map(User::toString).orElse("Unknown");
        System.out.println("User's details: " + name);
    }

    public Optional<User> findUserById(int id) {
        return switch (id) {
            case 101 -> Optional.of(new User(101, "John Doe"));
            default -> Optional.empty();
        };
    }

    public static record User(int id, String name) {
        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}