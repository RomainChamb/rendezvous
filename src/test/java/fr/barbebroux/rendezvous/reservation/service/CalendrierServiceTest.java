package fr.barbebroux.rendezvous.reservation.service;

import fr.barbebroux.rendezvous.reservation.exception.CreneauDejaExistantException;
import fr.barbebroux.rendezvous.reservation.model.Creneau;
import fr.barbebroux.rendezvous.reservation.port.CalendrierRepository;
import fr.barbebroux.rendezvous.reservation.repository.InMemoryCalendrierRepositoryTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class CalendrierServiceTest {

    private CalendrierRepository calendrierRepository;

    @BeforeEach
    void setUp() {
        calendrierRepository = new InMemoryCalendrierRepositoryTest();
    }

    @Test
    void leNouveauCreneauDoitApparaîtreDansLeCalendrierApresSonAjout() {
        // GIVEN
        Creneau nouveauCreneau = new Creneau("12/10/2024", "10:00", "10:30");

        // WHEN
        CalendrierService calendrierService = new CalendrierService(calendrierRepository);
        calendrierService.ajouterCreneau(nouveauCreneau);

        // THEN
        assertThat(calendrierService.recupererTousLesCreneauxDisponible().size()).isEqualTo(1);
        assertThat(calendrierService.recupererTousLesCreneauxDisponible().contains(nouveauCreneau)).isTrue();
    }

    @Test
    void doitLeverUneExceptionSiLeCreneauxEstDejaPresent() {
        // GIVEN
        Creneau creneau = new Creneau("12/10/2024", "10:00", "10:30");
        CalendrierService calendrierService = new CalendrierService(calendrierRepository);
        calendrierService.ajouterCreneau(creneau);

        // WHEN
        Creneau nouveauCreneau = new Creneau("12/10/2024", "10:00", "10:30");
        Throwable thrown = catchThrowable(() -> {
            calendrierService.ajouterCreneau(nouveauCreneau);
        });

        // THEN
        assertThat(thrown)
                .isInstanceOf(CreneauDejaExistantException.class)
                .hasMessage("Le créneau %s est déjà présent dans le calendrier", nouveauCreneau.toString());
    }

}
