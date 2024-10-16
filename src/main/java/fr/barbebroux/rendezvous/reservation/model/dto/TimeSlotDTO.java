package fr.barbebroux.rendezvous.reservation.model.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class TimeSlotDTO {

    private String date;
    private String startTime;
    private String endTime;

    public TimeSlotDTO() {
        this("","","");
    }

    public TimeSlotDTO(String date, String startTime, String endTime) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public TimeSlotDTO(LocalDate date, LocalTime startTime, LocalTime endTime) {
        this(date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), startTime.toString(), endTime.toString());
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String heureDebut) {
        this.startTime = heureDebut;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String heureFin) {
        this.endTime = heureFin;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof TimeSlotDTO that))
            return false;
        return Objects.equals(date, that.date) && Objects.equals(startTime, that.startTime) && Objects.equals(endTime, that.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, startTime, endTime);
    }
}
