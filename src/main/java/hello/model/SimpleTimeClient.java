package hello.model;

import hello.declaration.TimeClient;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record SimpleTimeClient(LocalDateTime dateAndTime) implements TimeClient {

    public SimpleTimeClient {
        this.dateAndTime = LocalDateTime.now();
    }

    @Override
    public void setTime(int hour, int minute, int second) {
        var currentDate = dateAndTime.toLocalDate();
        var timeToSet = LocalTime.of(hour, minute, second);
        this.dateAndTime = LocalDateTime.of(currentDate, timeToSet);
    }

    @Override
    public void setDate(int day, int month, int year) {
        var dateToSet = LocalDate.of(year, month, day);
        var currentTime = dateAndTime.toLocalTime();
        this.dateAndTime = LocalDateTime.of(dateToSet, currentTime);
    }

    @Override
    public void setDateAndTime(int day, int month, int year, int hour, int minute, int second) {
        var dateToSet = LocalDate.of(year, month, day);
        var timeToSet = LocalTime.of(hour, minute, second);
        this.dateAndTime = LocalDateTime.of(dateToSet, timeToSet);
    }

    @Override
    public LocalDateTime getLocalDateTime() {
        return dateAndTime;
    }

    @Override
    public String toString() {
        return Objects.toString(dateAndTime);
    }
}