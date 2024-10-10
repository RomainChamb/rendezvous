package fr.barbebroux.rendezvous.reservation.exception;

import fr.barbebroux.rendezvous.reservation.model.Creneau;

public class CreneauDejaExistantException extends RuntimeException {

    public CreneauDejaExistantException(Creneau creneau) {
        super(String.format("Le créneau %s est déjà présent dans le calendrier", creneau.toString()));
    }
}
