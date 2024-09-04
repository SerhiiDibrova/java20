package com.onlyfullstack.bean;

import java.util.Arrays;
import java.util.Objects;

/**
 * Represents a student with name, city, age, and courses.
 */
public class Student {

    private String name;
    private String city;
    private int age;
    private String[] courses;

    /**
     * Constructs a new Student object with the given parameters.
     *
     * @param name   the student's name
     * @param city   the student's city
     * @param age    the student's age
     * @param courses the student's courses
     */
    public Student(String name, String city, int age, String[] courses) {
        this.name = Objects.requireNonNull(name);
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.city = Objects.requireNonNull(city);
        if (city.isEmpty()) {
            throw new IllegalArgumentException("City cannot be empty");
        }
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }
        this.age = age;
        this.courses = Arrays.copyOf(courses, courses.length);
    }

    /**
     * Returns the student's courses.
     *
     * @return a copy of the student's courses array
     */
    public String[] getCourses() {
        return Arrays.copyOf(courses, courses.length);
    }

    /**
     * Sets the student's courses.
     *
     * @param courses the new courses for the student
     */
    public void setCourses(String[] courses) {
        if (courses == null) {
            throw new NullPointerException("Courses cannot be null");
        }
        this.courses = Arrays.copyOf(courses, courses.length);
    }

    /**
     * Returns the student's age.
     *
     * @return the student's age
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the student's age.
     *
     * @param age the new age for the student
     */
    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }
        this.age = age;
    }

    /**
     * Returns the student's name.
     *
     * @return the student's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the student's name.
     *
     * @param name the new name for the student
     */
    public void setName(String name) {
        if (name == null) {
            throw new NullPointerException("Name cannot be null");
        }
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }

    /**
     * Returns the student's city.
     *
     * @return the student's city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the student's city.
     *
     * @param city the new city for the student
     */
    public void setCity(String city) {
        if (city == null) {
            throw new NullPointerException("City cannot be null");
        }
        if (city.isEmpty()) {
            throw new IllegalArgumentException("City cannot be empty");
        }
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return age == student.age && Objects.equals(name, student.name) && Objects.equals(city, student.city) && Arrays.equals(courses, student.courses);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, city, age);
        result = 31 * result + Arrays.hashCode(courses);
        return result;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", age=" + age +
                ", courses=" + (courses == null || courses.length == 0 ? "[]" : Arrays.toString(courses)) +
                '}';
    }
}