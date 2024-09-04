package com.onlyfullstack.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CollectionsVsStreams {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>(Arrays.asList(new String[] {"only", "fullstack", "java", "spring", "automation"}));

        System.out.println("Collection Printing");
        for (String names : list) {
            Optional.ofNullable(names).ifPresent(System.out::println);
        }

        System.out.println("\n\nStream Printing");
        list.stream().forEach(Optional::ofNullable).forEach(System.out::println);
    }
}