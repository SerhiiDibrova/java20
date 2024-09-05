package com.winterbe.java8.samples;

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

        Predicate<String> predicate = s -> s.length() > 0;

        predicate.test("foo");              // true
        predicate.negate().test("foo");     // false

        Predicate<Boolean> nonNull = Objects::nonNull;
        Predicate<Boolean> isNull = Objects::isNull;

        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();

        // Functions

        Function<String, Integer> toInteger = Integer::valueOf;
        Function<String, String> backToString = toInteger.andThen(String::valueOf);

        backToString.apply("123");     // "123"

        // Suppliers

        Supplier<UUID> uuidSupplier = UUID::randomUUID;
        var uuid = uuidSupplier.get();   // new UUID
        System.out.println(uuid);

        record Person(String firstName, String lastName) {}

        // Consumers

        Consumer<Person> greeter = p -> System.out.println("Hello, " + p.firstName);
        greeter.accept(new Person("Luke", ""));

        // Comparators

        Comparator<String> comparator = (p1, p2) -> p1.compareTo(p2);

        var p1 = "John";
        var p2 = "Alice";

        System.out.println(comparator.compare(p1, p2));             // > 0
        System.out.println(comparator.reversed().compare(p1, p2));  // < 0

        // Runnables

        Runnable runnable = () -> System.out.println(UUID.randomUUID());
        runnable.run();

        // Callables

        Callable<UUID> callable = UUID::randomUUID;
        var result = callable.call();
    }
}