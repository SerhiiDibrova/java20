package com.onlyfullstack.stream;

import java.util.Arrays;
import java.util.OptionalInt;

public class ShortCircuit {

    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 11};

        if (numbers == null || numbers.length == 0) {
            System.out.println("Array is empty or null.");
            return;
        }

        System.out.println("*** Printing the first 5 elements of stream :");
        Arrays.stream(numbers)
                .limit(5)
                .forEach(System.out::println);

        OptionalInt findFirst = Arrays.stream(numbers).filter(i -> (i % 2) == 0)
                .findFirst();

        System.out.println("*** findFirst : " + findFirst.orElse(-1));

        OptionalInt findAny = Arrays.stream(numbers).filter(i -> (i % 2) == 0)
                .findAny();

        System.out.println("*** findAny : " + findAny.orElse(-1));

        System.out.println("*** All numbers are less than 12 : " + Arrays.stream(numbers).allMatch(i -> i < 12));
        System.out.println("*** Contains any number equal to 8 : " + Arrays.stream(numbers).anyMatch(i -> i == 8));
        System.out.println("*** All numbers are less than 10 : " + Arrays.stream(numbers).noneMatch(i -> i >= 10));
    }
}