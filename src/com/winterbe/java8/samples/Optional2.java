package com.winterbe.java8.samples;

import java.util.Optional;
import java.util.function.Supplier;

public class Optional2 {

    static record Outer(Nested nested) {
        public Outer() {
            this(new Nested());
        }
    }

    static record Nested(Inner inner) {
        public Nested() {
            this(new Inner());
        }
    }

    static record Inner(String foo) {
        public Inner() {
            this("boo");
        }
    }

    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }

    private static <T> Optional<T> resolve(Supplier<T> resolver) {
        try {
            T result = resolver.get();
            return Optional.ofNullable(result);
        } catch (NullPointerException e) {
            return Optional.empty();
        }
    }

    private static void test3() {
        var outer = new Outer();
        resolve(() -> outer.nested().inner().foo())
                .ifPresent(System.out::println);
    }

    private static void test2() {
        Optional.of(new Outer())
                .map(Outer::nested)
                .map(Nested::inner)
                .map(Inner::foo)
                .ifPresent(System.out::println);
    }

    private static void test1() {
        Optional.of(new Outer())
                .flatMap(o -> Optional.ofNullable(o.nested))
                .flatMap(n -> Optional.ofNullable(n.inner))
                .flatMap(i -> Optional.ofNullable(i.foo()))
                .ifPresent(System.out::println);
    }
}