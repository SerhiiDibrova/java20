package hello.declaration;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

public interface TimeClient {

    void setTime(int hour, int minute, int second);
    void setDate(int day, int month, int year);
    void setDateAndTime(int day, int month, int year, int hour, int minute, int second);
    LocalDateTime getLocalDateTime();

    static ZoneId getZoneId(String zoneString) {
        try {
            return ZoneId.of(zoneString);
        } catch (DateTimeException e) {
            Logger.getLogger(TimeClient.class.getName()).log(Level.SEVERE, "Invalid time zone: {0}; using default time zone instead.", zoneString);
            return ZoneId.systemDefault();
        }
    }

    default ZonedDateTime getZonedDateTime(String zoneString) {
        var localDateTime = getLocalDateTime();
        if (localDateTime == null) {
            throw new NullPointerException("Local date and time is null");
        }
        return ZonedDateTime.of(localDateTime, getZoneId(zoneString));
    }
}