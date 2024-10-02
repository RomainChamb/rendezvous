package fr.barbebroux.rendezvous.bdd;

import fr.barbebroux.rendezvous.reservation.port.Calendrier;
import fr.barbebroux.rendezvous.reservation.model.Creneau;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Etantdonné;
import io.cucumber.java.fr.Lorsque;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("fr/barbebroux/rendezvous")
@CucumberContextConfiguration
@SpringBootTest
public class PriseDeRendezVousSteps {

    @Autowired
    private Calendrier calendrier;


    @Etantdonné("un calendrier avec des créneaux disponibles")
    public void unCalendrierAvecCréneauxDisponibles(DataTable dates) {
        List<String> creneauxDisponibles = dates.asList(String.class);
        for (String creneau : creneauxDisponibles) {
            calendrier.ajouterCreneauDisponible(new Creneau(creneau));
        }
    }

    @Lorsque("Romain choisit le créneau {string}")
    public void romainChoisitUnCréneaux(String date) {
        Creneau creneau = new Creneau(date);
        calendrier.reserverRendezVous(creneau);
    }

    @Alors("Le créneau {string} n'est plus disponible")
    public void leCréneauNeDoitPlusApparaîtreDansLaListe(String date) {
        Creneau creneau = new Creneau(date);
        assertThat(calendrier.isCreneauDisponible(creneau)).isFalse();
    }
}
