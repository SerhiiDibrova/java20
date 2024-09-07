package com.winterbe.java8.samples;

import java.util.EnumSet;

public class Switch {

    public static void main(String[] args) {
        switchForInteger(3);
        switchWithMultipleCases(2);
        switchWithString("Apple");
        switchWithEnum(Day.WEDNESDAY);
    }

    // Basic switch statement with Integer
    private static void switchForInteger(int day) {
        var dayName = switch (day) {
            case 1 -> "Monday";
            case 2 -> "Tuesday";
            case 3 -> "Wednesday";
            default -> "Unknown";
        };
        System.out.println(dayName);
    }

    // Switch with multiple cases
    private static void switchWithMultipleCases(int number) {
        var result = switch (number) {
            case 1, 2 -> "One or Two";
            case 3 -> "Three";
            default -> "Other";
        };
        System.out.println(result);
    }

    // Switch with String
    private static void switchWithString(String fruit) {
        switch (fruit) {
            case "Apple" -> System.out.println("Apple selected");
            case "Banana" -> System.out.println("Banana selected");
            default -> System.out.println("""
                    Unknown fruit
                    Please try again""");
        }
    }

    // Switch with Enum
    private static void switchWithEnum(Day today) {
        var message = switch (today) {
            case MONDAY -> "Start of work week";
            case WEDNESDAY -> "Midweek";
            case FRIDAY -> "End of work week";
            default -> "Weekend";
        };
        System.out.println(message);
    }

    // Enum declaration for switchWithEnum method
    enum Day { MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY }
}