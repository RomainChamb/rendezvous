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
        creneaux.add(new Creneau("2024-10-02T08:00"));
        creneaux.add(new Creneau("2024-10-02T09:00"));
        creneaux.add(new Creneau("2024-10-02T10:00"));
        creneaux.add(new Creneau("2024-10-02T11:00"));
        creneaux.add(new Creneau("2024-10-02T12:00"));
        creneaux.add(new Creneau("2024-10-02T14:00"));
        creneaux.add(new Creneau("2024-10-02T15:00"));
        creneaux.add(new Creneau("2024-10-02T16:00"));
        creneaux.add(new Creneau("2024-10-02T17:00"));

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
}
