package fr.barbebroux.rendezvous.reservation.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public record Creneau (LocalDate date, LocalTime startTime, LocalTime endTime) {

    public Creneau(String date, String startTime, String endTime) {
        this(
                LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                LocalTime.parse(startTime, DateTimeFormatter.ofPattern("HH:mm")),
                LocalTime.parse(endTime, DateTimeFormatter.ofPattern("HH:mm"))
        );
    }

    public Creneau(LocalDate date, LocalTime startTime, LocalTime endTime) {
        if(endTime.isBefore(startTime)) throw new IllegalArgumentException("The endTime should be after the startTime");
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public LocalDate date() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(date.format(formatter), formatter);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Creneau creneau))
            return false;
        return Objects.equals(date, creneau.date) && Objects.equals(startTime, creneau.startTime) && Objects.equals(
                endTime, creneau.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, startTime, endTime);
    }
}
