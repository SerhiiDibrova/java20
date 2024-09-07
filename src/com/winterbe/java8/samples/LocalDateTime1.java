package com.winterbe.java8.samples;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Date;

public class LocalDateTime1 {

    public static void main(String[] args) {
        var sylvester = LocalDateTime.of(2014, Month.DECEMBER, 31, 23, 59, 59);

        System.out.println(sylvester.getDayOfWeek());      // WEDNESDAY

        System.out.println(sylvester.getMonth());          // DECEMBER

        System.out.println(sylvester.getLong(ChronoField.MINUTE_OF_DAY));    // 1439

        var instant = sylvester.atZone(ZoneId.systemDefault()).toInstant();

        System.out.println(Date.from(instant));     // Wed Dec 31 23:59:59 CET 2014


        var formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy - HH:mm");

        var parsed = LocalDateTime.parse("Nov 03, 2014 - 07:13", formatter);
        System.out.println(parsed.format(formatter));     // Nov 03, 2014 - 07:13
    }
}