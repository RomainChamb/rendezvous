package fr.barbebroux.rendezvous;

import io.cucumber.java.PendingException;
import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Etantdonnéque;
import io.cucumber.java.fr.Lorsque;

public class PriseRendezVousSteps {
    @Etantdonnéque("Blandine à définit {int} créneaux disponibles")
    public void blandineÀDéfinitCréneauxDisponibles(int arg0) {
        throw new PendingException();
    }

    @Lorsque("Romain consulte le calendrier de Blandine")
    public void romainConsulteLeCalendrierDeBlandine() {
        throw new PendingException();
    }

    @Alors("Romain doit voir {int} créneaux disponibles")
    public void romainDoitVoirCréneauxDisponibles(int arg0) {
        throw new PendingException();
    }
}
