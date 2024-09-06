package hello.declaration;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public record TimeClient(LocalDateTime localDateTime) {

    public void setTime(int hour, int minute, int second) {
        try {
            this.localDateTime = this.localDateTime.withHour(hour).withMinute(minute).withSecond(second);
        } catch (DateTimeException e) {
            System.err.println("Invalid time: " + hour + ":" + minute + ":" + second +
                    "; using default time instead.");
        }
    }

    public void setDate(int day, int month, int year) {
        try {
            this.localDateTime = this.localDateTime.withDayOfMonth(day).withMonth(month).withYear(year);
        } catch (DateTimeException e) {
            System.err.println("Invalid date: " + day + "/" + month + "/" + year +
                    "; using default date instead.");
        }
    }

    public void setDateAndTime(int day, int month, int year, int hour, int minute, int second) {
        try {
            this.localDateTime = this.localDateTime.withDayOfMonth(day).withMonth(month).withYear(year)
                    .withHour(hour).withMinute(minute).withSecond(second);
        } catch (DateTimeException e) {
            System.err.println("Invalid date and time: " + day + "/" + month + "/" + year +
                    " " + hour + ":" + minute + ":" + second +
                    "; using default date and time instead.");
        }
    }

    public static ZoneId getZoneId(String zoneString) {
        try {
            return ZoneId.of(zoneString);
        } catch (DateTimeException e) {
            System.err.println("Invalid time zone: " + zoneString +
                    "; using default time zone instead.");
            return ZoneId.systemDefault();
        }
    }

    public ZonedDateTime getZonedDateTime(String zoneString) {
        return ZonedDateTime.of(this.localDateTime, getZoneId(zoneString));
    }
}