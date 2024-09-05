package com.winterbe.java8.samples;

public class Lambda4 {

    static int outerStaticNum;
    int outerNum;

    void testScopes() {
        var num = 1;

        var stringConverter = (from) -> String.valueOf(from + num);
        var convert = stringConverter.convert(2);
        System.out.println(convert);    // 3

        var stringConverter2 = (from) -> {
            outerNum = 13;
            return String.valueOf(from);
        };

        var array = new String[1];
        var stringConverter3 = (from) -> {
            array[0] = "Hi there";
            return String.valueOf(from);
        };

        stringConverter3.convert(23);

        System.out.println(array[0]);
    }

    public static void main(String[] args) {
        new Lambda4().testScopes();
    }
}