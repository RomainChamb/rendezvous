package fr.barbebroux.rendezvous.reservation.repository;

import fr.barbebroux.rendezvous.reservation.model.TimeSlot;
import fr.barbebroux.rendezvous.reservation.port.CalendarRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryCalendarRepositoryTest implements CalendarRepository {

    private final List<TimeSlot> timeSlots = new ArrayList<>();

    @Override
    public List<TimeSlot> recupererTousLesCreneauxDisponibles() {
        return timeSlots;
    }

    @Override
    public void ajouterCreneauDisponible(TimeSlot timeSlot) {
        timeSlots.add(timeSlot);
    }

    @Override
    public boolean creneauExiste(TimeSlot timeSlot) {
        return timeSlots.contains(timeSlot);
    }
}
