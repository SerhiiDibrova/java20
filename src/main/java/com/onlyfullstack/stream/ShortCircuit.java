package com.onlyfullstack.stream;

import java.util.Arrays;
import java.util.OptionalInt;

public class ShortCircuit {

    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 11};

        System.out.println("*** Printing the first 5 elements of stream :");
        Arrays.stream(numbers)
                .limit(5)
                .forEach(System.out::println);

        OptionalInt findFirstEvenNumber = Arrays.stream(numbers).filter(i -> (i % 2) == 0)
                .findFirst();

        if (findFirstEvenNumber.isPresent()) {
            System.out.println("*** First even number found: " + findFirstEvenNumber.getAsInt());
        } else {
            System.out.println("*** No even numbers found");
        }

        OptionalInt findAnyEvenNumber = Arrays.stream(numbers).filter(i -> (i % 2) == 0)
                .findAny();

        if (findAnyEvenNumber.isPresent()) {
            System.out.println("*** Any even number found: " + findAnyEvenNumber.getAsInt());
        } else {
            System.out.println("*** No even numbers found");
        }

        boolean allNumbersLessThan12 = Arrays.stream(numbers).allMatch(i -> i < 12);
        System.out.println("*** All numbers are less than 12 : " + allNumbersLessThan12);

        boolean containsNumberGreaterThan10 = Arrays.stream(numbers).anyMatch(i -> i > 10);
        System.out.println("*** Contains any numbers greater than 10 : " + containsNumberGreaterThan10);

        boolean noNumbersGreaterThan10 = Arrays.stream(numbers).noneMatch(i -> i > 10);
        System.out.println("*** All numbers are less than or equal to 10 : " + noNumbersGreaterThan10);
    }
}