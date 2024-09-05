package com.onlyfullstack.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CollectionsVsStreams {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>(Arrays.asList(new String[] {"only", "fullstack", "java", "spring", "automation"}));

        System.out.println("Collection Printing");
        for (var names : list) {
            System.out.println(names);
        }

        System.out.println("\n\nStream Printing");
        list.stream().forEach(System.out::println);

        String choice = "only";
        int result = switch (choice) {
            case "only" -> 1;
            case "fullstack" -> 2;
            default -> 0;
        };
        System.out.println("Switch Expression Result: " + result);

        record Person(String name, int age) {}
        var person = new Person("John Doe", 30);
        System.out.println(person.name() + ", " + person.age());

        String textBlock = """
                This is a multi-line string.
                It can span multiple lines.
                """;
        System.out.println(textBlock);
    }
}