package com.onlyfullstack.stream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectionsVsStreams {

    public static void main(String[] args) {
        List<String> list = Stream.of("only", "fullstack", "java", "spring", "automation")
                .collect(Collectors.toList());

        System.out.println("Collection Printing");
        for (String names : list) {
            System.out.println(names);
        }

        System.out.println("\n\nStream Printing");
        list.stream().forEach(System.out::println);
    }
}