package fr.barbebroux.rendezvous.reservation.repository;

import fr.barbebroux.rendezvous.reservation.model.TimeSlot;
import fr.barbebroux.rendezvous.reservation.port.CalendarRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryCalendarRepositoryTest implements CalendarRepository {

    private final List<TimeSlot> timeSlots = new ArrayList<>();
    private final List<TimeSlot> bookedTimeSlots = new ArrayList<>();

    @Override
    public List<TimeSlot> fetchAllAvailableTimeSlots() {
        return timeSlots;
    }

    @Override
    public void addNewTimeSlot(TimeSlot timeSlot) {
        timeSlots.add(timeSlot);
    }

    @Override
    public boolean creneauExiste(TimeSlot timeSlot) {
        return timeSlots.contains(timeSlot);
    }

    @Override
    public void bookAppointment(TimeSlot timeSlot) {
        timeSlots.remove(timeSlot);
        bookedTimeSlots.add(timeSlot);
    }
}
