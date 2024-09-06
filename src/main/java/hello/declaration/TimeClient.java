package hello.declaration;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public interface TimeClient {

    void setTime(int hour, int minute, int second);
    void setDate(int day, int month, int year);
    void setDateAndTime(int day, int month, int year, int hour, int minute, int second);
    LocalDateTime getLocalDateTime();

    static ZoneId getZoneId(String zoneString) {
        try {
            return ZoneId.of(zoneString);
        } catch (DateTimeException e) {
            System.err.println("Invalid time zone: " + zoneString +
                    "; using default time zone instead.");
            return ZoneId.systemDefault();
        }
    }

    default ZonedDateTime getZonedDateTime(String zoneString) {
        return ZonedDateTime.of(getLocalDateTime(), getZoneId(zoneString));
    }
}

record TimeClientImpl(LocalDateTime localDateTime, ZoneId zoneId) implements TimeClient {

    @Override
    public void setTime(int hour, int minute, int second) {
        this = new TimeClientImpl(localDateTime.withHour(hour).withMinute(minute).withSecond(second), zoneId);
    }

    @Override
    public void setDate(int day, int month, int year) {
        this = new TimeClientImpl(localDateTime.withYear(year).withMonth(month).withDayOfMonth(day), zoneId);
    }

    @Override
    public void setDateAndTime(int day, int month, int year, int hour, int minute, int second) {
        this = new TimeClientImpl(LocalDateTime.of(year, month, day, hour, minute, second), zoneId);
    }

    @Override
    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }
}