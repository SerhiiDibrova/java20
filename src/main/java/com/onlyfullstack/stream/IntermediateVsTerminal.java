package com.onlyfullstack.stream;

import java.util.List;
import java.util.concurrent.CompletableFuture;
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
        Stream<String> studentStream = students.stream().map(student -> 
            Optional.ofNullable(student)
                .map(Student::getName)
                .map(String::toUpperCase)
                .orElse("")
        );

        System.out.println("After map statement");
        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).thenRun(() -> {
            System.out.println("Thread is in Running state now");
            studentStream.collect(Collectors.toList());
            System.out.println("######## Ending the execution of lazyIntermediateOperations() ######## ");
        });
    }
}