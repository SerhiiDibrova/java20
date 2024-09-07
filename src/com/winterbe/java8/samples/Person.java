package com.winterbe.java8.samples;

import java.util.Objects;

public record Person(String name, int age) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return """
                Person{
                    name='%s',
                    age=%d
                }
                """.formatted(name, age);
    }
}