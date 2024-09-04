package com.onlyfullstack.stream;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.onlyfullstack.bean.Student;
import com.onlyfullstack.util.JavaInputFixture;

public class Stream {

    private static final String SAURABH = "Saurabh";

    public static void main(String[] args) {
        List<Student> students = JavaInputFixture.createList();

        if (students != null && !students.isEmpty()) {
            filter(students);
            mapAndCollect(students);
            peek(students);
        } else {
            System.out.println("No students found.");
        }
    }

    private static void mapAndCollect(List<Student> students) {
        System.out.println("Executing mapAndCollect()...");
        try {
            // get the age of Student whose name is Saurabh
            int age = students.stream()
                    .filter(student -> SAURABH.equals(student.getName()))
                    .map(Student::getAge)
                    .findFirst().orElseThrow();

            System.out.printf("Age of %s is %d\n", SAURABH, age);

            Set<String> names = students.stream()
                    .map(Student::getName)
                    .collect(Collectors.toSet());

            System.out.printf("All the names from the list are: %s\n", names);

            Set<String> courses = students.stream()
                    .flatMap(Function.identity())
                    .map(Student::getCourses)
                    .flatMap(List::stream)
                    .collect(Collectors.toSet());

            System.out.printf("All the courses from the list are: %s\n", courses);
        } catch (Exception e) {
            System.err.println("Error occurred during mapAndCollect(): " + e.getMessage());
        }
    }

    private static void filter(List<Student> students) {
        System.out.println("Executing filter()...");
        try {
            // Print all the students who lives in Pune
            System.out.println("Students who live in Pune:");
            students.stream()
                    .filter(student -> "Pune".equals(student.getCity()))
                    .forEach(System.out::println);

            // Find the student whose name is Saurabh and return null if not found
            System.out.println("Student whose name is Saurabh:");
            Student stud = students.stream()
                    .filter(student -> SAURABH.equals(student.getName()))
                    .findFirst().orElse(null);

            if (stud != null) {
                System.out.println(stud);
            } else {
                System.out.println("No student found with name " + SAURABH);
            }
        } catch (Exception e) {
            System.err.println("Error occurred during filter(): " + e.getMessage());
        }
    }

    private static void peek(List<Student> students) {
        System.out.println("Executing peek()...");
        try {
            students.stream()
                    .peek(stud -> {
                        if (stud != null) {
                            System.out.println("Processing Student Name: " + stud.getName());
                        } else {
                            System.out.println("Student object is null.");
                        }
                    })
                    .filter(student -> student != null && "Mumbai".equals(student.getCity()))
                    .peek(stud -> {
                        if (stud != null) {
                            System.out.println("Filtered Student Name: " + stud.getName());
                        } else {
                            System.out.println("Student object is null.");
                        }
                    })
                    .forEach(System.out::println);
        } catch (Exception e) {
            System.err.println("Error occurred during peek(): " + e.getMessage());
        }
    }
}