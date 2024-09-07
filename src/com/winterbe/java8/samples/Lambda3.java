package com.winterbe.java20.samples;

import java.util.Comparator;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Lambda3 {

    @FunctionalInterface
    interface Fun {
        void foo();
    }

    public static void main(String[] args) throws Exception {

        // Predicates

        var predicate = (s) -> s.length() > 0;

        if(predicate.test("foo")) {
            System.out.println(true); 
        } else {
            System.out.println(false);
        }
        
        if(!predicate.negate().test("foo")) {
            System.out.println(false); 
        } else {
            System.out.println(true);
        }

        var nonNull = Objects::nonNull;
        var isNull = Objects::isNull;

        var isEmpty = String::isEmpty;
        var isNotEmpty = isEmpty.negate();

        // Functions

        var toInteger = Integer::valueOf;
        var backToString = toInteger.andThen(String::valueOf);

        if(backToString.apply("123").equals("123")) {
            System.out.println(true); 
        } else {
            System.out.println(false);
        }

        // Suppliers

        var personSupplier = Person::new;
        try {
            var person = personSupplier.get();
            System.out.println(person.firstName() + " " + person.lastName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Consumers

        var greeter = (p) -> System.out.println("Hello, " + p.firstName());
        try {
            greeter.accept(new Person("Luke", "Skywalker"));
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

        // Comparators

        var comparator = (p1, p2) -> p1.firstName().compareTo(p2.firstName());

        var p1 = new Person("John", "Doe");
        var p2 = new Person("Alice", "Wonderland");

        if(comparator.compare(p1, p2) > 0) {
            System.out.println(true); 
        } else {
            System.out.println(false);
        }
        
        if(comparator.reversed().compare(p1, p2) < 0) {
            System.out.println(true); 
        } else {
            System.out.println(false);
        }

        // Runnables

        var runnable = () -> System.out.println(UUID.randomUUID());
        runnable.run();

        // Callables

        var callable = UUID::randomUUID;
        try {
            var uuid = callable.call();
            System.out.println(uuid);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    record Person(String firstName, String lastName) {}
}