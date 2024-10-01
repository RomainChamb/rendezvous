package fr.barbebroux.rendezvous.bdd;

import fr.barbebroux.rendezvous.reservation.Calendrier;
import fr.barbebroux.rendezvous.reservation.Creneau;
import fr.barbebroux.rendezvous.reservation.Praticien;
import io.cucumber.java.PendingException;
import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Etantdonnéque;
import io.cucumber.java.fr.Lorsque;

import static org.assertj.core.api.Assertions.assertThat;

public class PriseDeRendezVousSteps {

    private Praticien blandine;

    @Etantdonnéque("Blandine a définit {int} créneaux disponibles")
    public void blandineADéfinitCréneauxDisponibles(int nombreDeCreneau) {
        Calendrier calendrier = new Calendrier();
        Creneau creneau1 = new Creneau("2024-10-08T10:00");
        Creneau creneau2 = new Creneau("2024-10-08T11:00");
        Creneau creneau3 = new Creneau("2024-10-08T12:00");
        calendrier.ajouterCreneauDisponible(creneau1);
        calendrier.ajouterCreneauDisponible(creneau2);
        calendrier.ajouterCreneauDisponible(creneau3);
        blandine = new Praticien(calendrier);
        throw new PendingException();
    }

    @Lorsque("Romain choisit un créneaux")
    public void romainChoisitUnCréneaux() {
        Creneau creneau = new Creneau("2024-10-08T11:00");
        blandine.reserverRendezVous(creneau);
    }

    @Alors("Le créneau ne doit plus apparaître dans la liste")
    public void leCréneauNeDoitPlusApparaîtreDansLaListe() {
        Creneau creneau = new Creneau("2024-10-08T11:00");
        assertThat(blandine.isCreneauDisponible(creneau)).isFalse();
    }
}
