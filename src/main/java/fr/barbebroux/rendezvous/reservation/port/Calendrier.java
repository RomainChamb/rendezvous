package fr.barbebroux.rendezvous.reservation.port;

import fr.barbebroux.rendezvous.reservation.model.Creneau;



public interface Calendrier {

    public boolean ajouterCreneauDisponible(Creneau creneau);

    public boolean reserverRendezVous(Creneau creneau);

    public boolean isCreneauDisponible(Creneau creneau);
}
