package fr.barbebroux.rendezvous.reservation.port;

import fr.barbebroux.rendezvous.reservation.model.Creneau;



public interface Calendrier {

    boolean ajouterCreneauDisponible(Creneau creneau);

    boolean reserverRendezVous(Creneau creneau);

    boolean isCreneauDisponible(Creneau creneau);

    boolean isCreneauReserve(String nomDuPatient, Creneau creneau);
}
