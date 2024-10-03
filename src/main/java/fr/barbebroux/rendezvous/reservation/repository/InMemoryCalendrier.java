package fr.barbebroux.rendezvous.reservation.repository;

import fr.barbebroux.rendezvous.reservation.model.Creneau;
import fr.barbebroux.rendezvous.reservation.port.Calendrier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Component
public class InMemoryCalendrier implements Calendrier {

    private final Set<Creneau> creneaux = new HashSet<>();

    public InMemoryCalendrier() {
        creneaux.add(new Creneau("2024-10-02", "08:00"));
        creneaux.add(new Creneau("2024-10-02", "09:00"));
        creneaux.add(new Creneau("2024-10-02", "10:00"));
        creneaux.add(new Creneau("2024-10-02", "11:00"));
        creneaux.add(new Creneau("2024-10-02", "12:00"));
        creneaux.add(new Creneau("2024-10-02", "14:00"));
        creneaux.add(new Creneau("2024-10-02", "15:00"));
        creneaux.add(new Creneau("2024-10-02", "16:00"));
        creneaux.add(new Creneau("2024-10-02", "17:00"));

    }

    @Override
    public boolean ajouterCreneauDisponible(Creneau creneau) {
        return creneaux.add(creneau);
    }

    @Override
    public boolean reserverRendezVous(Creneau creneau) {
        return creneaux.remove(creneau);
    }

    @Override
    public boolean isCreneauDisponible(Creneau creneau) {
        return creneaux.contains(creneau);
    }

    @Override
    public boolean isCreneauReserve(String nomDuPatient, Creneau creneau) {
        return false;
    }
}
