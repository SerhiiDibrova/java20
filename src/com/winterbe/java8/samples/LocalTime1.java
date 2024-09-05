package com.winterbe.java8.samples;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;

public class LocalTime1 {

    public static void main(String[] args) {
        var clock = Clock.systemDefaultZone();
        long t0 = clock.millis();
        System.out.println(t0);

        var instant = clock.instant();

        var zone1 = ZoneId.of("Europe/Berlin");
        var zone2 = ZoneId.of("Brazil/East");

        System.out.println(zone1.getRules());
        System.out.println(zone2.getRules());

        var now1 = LocalTime.now(zone1);
        var now2 = LocalTime.now(zone2);

        System.out.println(now1);
        System.out.println(now2);

        System.out.println(now1.isBefore(now2));  

        long hoursBetween = ChronoUnit.HOURS.between(now1, now2);
        long minutesBetween = ChronoUnit.MINUTES.between(now1, now2);
        System.out.println(hoursBetween);
        System.out.println(minutesBetween);

        var now = LocalTime.now();
        System.out.println(now);

        var late = LocalTime.of(23, 59, 59);
        System.out.println(late);

        var germanFormatter = DateTimeFormatter
                .ofLocalizedTime(FormatStyle.SHORT)
                .withLocale(Locale.GERMAN);

        var leetTime = LocalTime.parse("13:37", germanFormatter);
        System.out.println(leetTime);

        Date legacyDate = Date.from(instant);
    }
}