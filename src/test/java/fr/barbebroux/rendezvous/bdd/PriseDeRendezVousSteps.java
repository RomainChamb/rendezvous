package fr.barbebroux.rendezvous.bdd;

import fr.barbebroux.rendezvous.reservation.port.Calendrier;
import fr.barbebroux.rendezvous.reservation.model.Creneau;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
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
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("fr/barbebroux/rendezvous")
@CucumberContextConfiguration
@SpringBootTest
public class PriseDeRendezVousSteps {

    @Autowired
    private Calendrier calendrier;

    @DataTableType
    public Creneau defineCreneau(Map<String, String> entry) {
        return new Creneau(entry.get("date"), entry.get("heure"));
    }

    @Etantdonné("un calendrier avec des créneaux disponibles")
    public void unCalendrierAvecCréneauxDisponibles(List<Creneau> creneauxDisponibles) {
        //List<String> creneauxDisponibles = dates.asList(String.class);
        for (Creneau creneau : creneauxDisponibles) {
            calendrier.ajouterCreneauDisponible(creneau);
        }
    }

    @Lorsque("Romain choisit le créneau {string} à {string}")
    public void romainChoisitUnCréneaux(String date, String heure) {
        Creneau creneau = new Creneau(date, heure);
        calendrier.reserverRendezVous(creneau);
    }

    @Alors("Le créneau {string} à {string} n'est plus disponible")
    public void leCréneauNeDoitPlusApparaîtreDansLaListe(String date, String heure) {
        Creneau creneau = new Creneau(date, heure);
        assertThat(calendrier.isCreneauDisponible(creneau)).isFalse();
    }
}
