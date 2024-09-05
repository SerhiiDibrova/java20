package com.winterbe.java8.samples;

import java.util.Optional;
import java.util.function.Supplier;

public class Optional2 {

    static record Outer(Nested nested) {
        public Nested getNested() {
            return nested;
        }
    }

    static record Nested(Inner inner) {
        public Inner getInner() {
            return inner;
        }
    }

    static record Inner(String foo) {
        public String getFoo() {
            return foo;
        }
    }

    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }

    public static <T> Optional<T> resolve(Supplier<T> resolver) {
        try {
            T result = resolver.get();
            return Optional.ofNullable(result);
        } catch (NullPointerException e) {
            return Optional.empty();
        }
    }

    private static void test3() {
        var outer = new Outer(new Nested(new Inner("boo")));
        resolve(() -> outer.getNested().getInner().getFoo())
                .ifPresent(System.out::println);
    }

    private static void test2() {
        Optional.of(new Outer(new Nested(new Inner("boo"))))
                .map(Outer::getNested)
                .map(Nested::getInner)
                .map(Inner::getFoo)
                .ifPresent(System.out::println);
    }

    private static void test1() {
        Optional.of(new Outer(new Nested(new Inner("boo"))))
                .flatMap(o -> Optional.ofNullable(o.nested))
                .flatMap(n -> Optional.ofNullable(n.inner))
                .flatMap(i -> Optional.ofNullable(i.foo))
                .ifPresent(System.out::println);
    }
}