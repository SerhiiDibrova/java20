package hello.model;

import hello.declaration.TimeClient;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class SimpleTimeClient implements TimeClient {
    private final LocalDateTime dateAndTime;

    public SimpleTimeClient() {
        this.dateAndTime = LocalDateTime.now();
    }

    @Override
    public void setTime(int hour, int minute, int second) {
        var currentDate = LocalDate.from(dateAndTime);
        var timeToSet = LocalTime.of(hour, minute, second);
        dateAndTime = LocalDateTime.of(currentDate, timeToSet); // This line will not compile because dateAndTime is final
    }

    @Override
    public void setDate(int day, int month, int year) {
        var dateToSet = LocalDate.of(year, month, day);
        var currentTime = LocalTime.from(dateAndTime);
        dateAndTime = LocalDateTime.of(dateToSet, currentTime); // This line will not compile because dateAndTime is final
    }

    @Override
    public void setDateAndTime(int day, int month, int year, int hour, int minute, int second) {
        var dateToSet = LocalDate.of(year, month, day);
        var timeToSet = LocalTime.of(hour, minute, second);
        dateAndTime = LocalDateTime.of(dateToSet, timeToSet); // This line will not compile because dateAndTime is final
    }

    @Override
    public LocalDateTime getLocalDateTime() {
        return dateAndTime;
    }

    @Override
    public String toString() {
        return dateAndTime.toString();
    }
}

// The above code does not compile due to the final keyword on dateAndTime variable. 
// To fix this, we need to create a new instance of SimpleTimeClient with the updated date and time.

package hello.model;

import hello.declaration.TimeClient;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class SimpleTimeClient implements TimeClient {
    private final LocalDateTime dateAndTime;

    public SimpleTimeClient() {
        this.dateAndTime = LocalDateTime.now();
    }

    public SimpleTimeClient(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    @Override
    public void setTime(int hour, int minute, int second) {
        var currentDate = LocalDate.from(dateAndTime);
        var timeToSet = LocalTime.of(hour, minute, second);
        SimpleTimeClient updatedSimpleTimeClient = new SimpleTimeClient(LocalDateTime.of(currentDate, timeToSet));
    }

    @Override
    public void setDate(int day, int month, int year) {
        var dateToSet = LocalDate.of(year, month, day);
        var currentTime = LocalTime.from(dateAndTime);
        SimpleTimeClient updatedSimpleTimeClient = new SimpleTimeClient(LocalDateTime.of(dateToSet, currentTime));
    }

    @Override
    public void setDateAndTime(int day, int month, int year, int hour, int minute, int second) {
        var dateToSet = LocalDate.of(year, month, day);
        var timeToSet = LocalTime.of(hour, minute, second);
        SimpleTimeClient updatedSimpleTimeClient = new SimpleTimeClient(LocalDateTime.of(dateToSet, timeToSet));
    }

    @Override
    public LocalDateTime getLocalDateTime() {
        return dateAndTime;
    }

    @Override
    public String toString() {
        return dateAndTime.toString();
    }
}