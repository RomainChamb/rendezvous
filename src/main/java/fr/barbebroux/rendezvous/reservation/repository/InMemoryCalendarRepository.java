package fr.barbebroux.rendezvous.reservation.repository;

import fr.barbebroux.rendezvous.reservation.model.TimeSlot;
import fr.barbebroux.rendezvous.reservation.port.CalendarRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class InMemoryCalendarRepository implements CalendarRepository {

    private final List<TimeSlot> timeSlots = Stream.of(
            new TimeSlot("10/10/2024", "10:00", "10:30"),
            new TimeSlot("10/10/2024", "10:30", "11:00"),
            new TimeSlot("11/10/2024", "11:00", "11:30"),
            new TimeSlot("11/10/2024", "14:00", "14:30")).collect(Collectors.toList());


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
}
