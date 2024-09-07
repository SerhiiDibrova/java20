package com.winterbe.java20.samples;

import java.util.EnumSet;
import java.util.Objects;

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
        if (Objects.isNull(fruit)) {
            System.out.println("Unknown fruit");
            return;
        }
        var message = switch (fruit) {
            case "Apple" -> """
                    Apple selected
                    This is a multi-line string""";
            case "Banana" -> "Banana selected";
            default -> "Unknown fruit";
        };
        System.out.println(message);
    }

    // Switch with Enum
    private static void switchWithEnum(Day today) {
        if (Objects.isNull(today)) {
            System.out.println("Weekend");
            return;
        }
        var message = switch (today) {
            case MONDAY -> """
                    Start of work week
                    This is a multi-line string""";
            case WEDNESDAY -> "Midweek";
            case FRIDAY -> "End of work week";
            default -> "Weekend";
        };
        System.out.println(message);
    }

    // Enum declaration for switchWithEnum method
    enum Day { MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY }
}