package com.winterbe.java8.samples;

import java.util.function.Function;

public class Lambda4 {

    static int outerStaticNum;
    int outerNum;

    void testScopes() {
        var num = 1;

        Function<Integer, String> stringConverter = (from) -> String.valueOf(from + num);
        var convert = stringConverter.apply(2);
        System.out.println(convert); 

        Function<Integer, String> stringConverter2 = (from) -> {
            outerNum = 13;
            return String.valueOf(from);
        };

        var array = new String[1];
        Function<Integer, String> stringConverter3 = (from) -> {
            array[0] = "Hi there";
            return String.valueOf(from);
        };

        stringConverter3.apply(23);

        System.out.println(array[0]);
    }

    public static void main(String[] args) {
        new Lambda4().testScopes();
    }
}