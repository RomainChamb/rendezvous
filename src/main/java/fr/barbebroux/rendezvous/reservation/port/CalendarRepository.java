package fr.barbebroux.rendezvous.reservation.port;

import fr.barbebroux.rendezvous.reservation.model.TimeSlot;

import java.util.List;

public interface CalendarRepository {

    List<TimeSlot> fetchAllAvailableTimeSlots();

    void addNewTimeSlot(TimeSlot timeSlot);

    boolean creneauExiste(TimeSlot timeSlot);

    void bookAppointment(TimeSlot timeSlot);
}
