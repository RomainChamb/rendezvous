package fr.barbebroux.rendezvous.reservation.port;

import fr.barbebroux.rendezvous.reservation.model.Creneau;

import java.util.List;

public interface CalendrierRepository {

    List<Creneau> recupererTousLesCreneauxDisponibles();

    void ajouterCreneauDisponible(Creneau creneau);

    boolean creneauExiste(Creneau creneau);
}
