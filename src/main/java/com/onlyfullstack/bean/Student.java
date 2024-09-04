package com.onlyfullstack.bean;

import java.util.Arrays;
import java.util.Objects;

public record Student(String name, String city, int age, String[] courses) {

    public Student {
        Objects.requireNonNull(name, "Name cannot be null");
        Objects.requireNonNull(city, "City cannot be null");
        Objects.requireNonNull(courses, "Courses cannot be null");
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Student student = (Student) obj;
        return age == student.age && Objects.equals(name, student.name) && Objects.equals(city, student.city)
                && Arrays.equals(courses, student.courses);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, city, age);
        result = 31 * result + Arrays.hashCode(courses);
        return result;
    }

    @Override
    public String toString() {
        return """
                Student [
                    name=%s,
                    city=%s,
                    age=%d,
                    courses=%s
                ]
                """.formatted(name, city, age, Arrays.toString(courses));
    }
}