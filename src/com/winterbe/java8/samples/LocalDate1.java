package com.winterbe.java8.samples;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class LocalDate1 {

    public static void main(String[] args) {
        var today = LocalDate.now();
        var tomorrow = today.plus(1, ChronoUnit.DAYS);
        var yesterday = tomorrow.minusDays(2);

        System.out.println(today);
        System.out.println(tomorrow);
        System.out.println(yesterday);

        var independenceDay = LocalDate.of(2014, Month.JULY, 4);
        var dayOfWeek = independenceDay.getDayOfWeek();
        System.out.println(dayOfWeek);    

        var germanFormatter = DateTimeFormatter
                .ofLocalizedDate(FormatStyle.MEDIUM)
                .withLocale(Locale.GERMAN);

        var xmas = LocalDate.parse("24.12.2014", germanFormatter);
        System.out.println(xmas);   
    }
}