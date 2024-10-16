package fr.barbebroux.rendezvous.reservation.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public record TimeSlot(LocalDate date, LocalTime startTime, LocalTime endTime) {

    public TimeSlot(String date, String startTime, String endTime) {
        this(
                LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                LocalTime.parse(startTime, DateTimeFormatter.ofPattern("HH:mm")),
                LocalTime.parse(endTime, DateTimeFormatter.ofPattern("HH:mm"))
        );
    }

    public TimeSlot(LocalDate date, LocalTime startTime, LocalTime endTime) {
        if(checkTimeChronology(startTime, endTime)) throw new IllegalArgumentException("The endTime should be after the startTime");
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public LocalDate date() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(date.format(formatter), formatter);
    }

    private boolean checkTimeChronology(LocalTime startTime, LocalTime endTime) {
        return endTime.isBefore(startTime) || endTime.equals(startTime);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof TimeSlot timeSlot))
            return false;
        return Objects.equals(date, timeSlot.date) && Objects.equals(startTime, timeSlot.startTime) && Objects.equals(
                endTime, timeSlot.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, startTime, endTime);
    }
}
