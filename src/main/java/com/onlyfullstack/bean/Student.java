package com.onlyfullstack.bean;

import java.util.Arrays;
import java.util.Objects;

public record Student(String name, String city, int age, String[] courses) {

    public static void validateAge(int age) {
        if (age < 0 || age > 150) {
            throw new IllegalArgumentException("Invalid age");
        }
    }

    public Student {
        Objects.requireNonNull(name);
        Objects.requireNonNull(city);
        Objects.requireNonNull(courses);
        validateAge(age);
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