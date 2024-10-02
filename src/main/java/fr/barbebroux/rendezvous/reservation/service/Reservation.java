package fr.barbebroux.rendezvous.reservation.service;

import fr.barbebroux.rendezvous.reservation.model.Creneau;
import fr.barbebroux.rendezvous.reservation.port.Calendrier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Reservation {

    private final Calendrier calendrier;

    @Autowired
    public Reservation(Calendrier calendrier) {
        this.calendrier = calendrier;
    }

    public boolean ajouterUnCreneauDisponible(Creneau creneau) {
        return calendrier.ajouterCreneauDisponible(creneau);
    }

    public boolean prendreRendezVous(Creneau creneau) {
        return calendrier.reserverRendezVous(creneau);
    }
}
