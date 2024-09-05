package com.onlyfullstack.stream;

import java.util.Arrays;
import java.util.OptionalInt;

public class ShortCircuit {

    public static void main(String[] args) {
        int [] arr = {1,2,3,4,5,6,7,8,9,11};
        
        System.out.println("*** Printing the first 5 elements of stream :");
        Arrays.stream(arr)
                .limit(5)
                .forEach(System.out::println);
        
        OptionalInt findFirst = Arrays.stream(arr).filter(i -> (i%2) == 0)
                .findFirst();
        
        System.out.println("*** findFirst : " + findFirst.orElse(-1));
        
        OptionalInt findAny = Arrays.stream(arr).filter(i -> (i%2) == 0)
                .findAny();
        
        System.out.println("*** findAny : " + findAny.orElse(-1));
        
        System.out.println("*** All numbers are less than 12 : " + Arrays.stream(arr).allMatch(i-> i < 12));
        System.out.println("*** Contains any numbers greater than or equal to 10 : " + Arrays.stream(arr).anyMatch(i-> i >= 10));
        System.out.println("*** All numbers are less than 10 : " + Arrays.stream(arr).noneMatch(i-> i >= 10));
    }
}