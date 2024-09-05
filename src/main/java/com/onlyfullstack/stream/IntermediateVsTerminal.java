package com.onlyfullstack.stream;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.onlyfullstack.bean.Student;
import com.onlyfullstack.util.JavaInputFixture;

public class IntermediateVsTerminal {

    public static void main(String[] args) {
        List<Student> students = JavaInputFixture.createList();
        lazyIntermediateOperations(students);
    }

    private static void lazyIntermediateOperations(List<Student> students) {
        System.out.println("######## Executing lazyIntermediateOperations() : ######## ");
        Stream<String> studentStream = students.stream().map(student -> {
            System.out.printf("In Map : %s\n", student.getName());
            return student.getName().toUpperCase();
        });

        System.out.println("After map statement");
        try {
            Thread.sleep(Duration.ofSeconds(5).toMillis());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        List<String> result = studentStream.collect(Collectors.toList());
        System.out.println(result);
        System.out.println("######## Ending the execution of lazyIntermediateOperations() ######## ");
    }
}