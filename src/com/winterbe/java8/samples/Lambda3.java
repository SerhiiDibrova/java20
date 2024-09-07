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

        Predicate<String> predicate = (s) -> s.length() > 0;

        var result1 = predicate.test("foo");              // true
        var result2 = predicate.negate().test("foo");     // false

        Predicate<Boolean> nonNull = Objects::nonNull;
        Predicate<Boolean> isNull = Objects::isNull;

        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();

        // Functions

        Function<String, Integer> toInteger = Integer::valueOf;
        Function<String, String> backToString = toInteger.andThen(String::valueOf);

        var result3 = backToString.apply("123");     // "123"

        // Suppliers

        Supplier<Person> personSupplier = Person::new;
        var person = personSupplier.get();   // new Person

        // Consumers

        Consumer<Person> greeter = (p) -> System.out.println("Hello, " + p.firstName);
        greeter.accept(new Person("Luke", "Skywalker"));

        // Comparators

        Comparator<Person> comparator = (p1, p2) -> p1.firstName.compareTo(p2.firstName);

        var p1 = new Person("John", "Doe");
        var p2 = new Person("Alice", "Wonderland");

        var result4 = comparator.compare(p1, p2);             // > 0
        var result5 = comparator.reversed().compare(p1, p2);  // < 0

        // Runnables

        Runnable runnable = () -> System.out.println(UUID.randomUUID());
        runnable.run();

        // Callables

        Callable<UUID> callable = UUID::randomUUID;
        var uuid = callable.call();
    }

    record Person(String firstName, String lastName) {}
}