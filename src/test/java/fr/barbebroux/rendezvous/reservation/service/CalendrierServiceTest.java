package fr.barbebroux.rendezvous.reservation.service;

import fr.barbebroux.rendezvous.reservation.model.Creneau;
import fr.barbebroux.rendezvous.reservation.model.dto.CreneauDTO;
import fr.barbebroux.rendezvous.reservation.port.CalendrierRepository;
import fr.barbebroux.rendezvous.reservation.repository.InMemoryCalendrierRepositoryTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class CalendrierServiceTest {

    private CalendrierRepository calendrierRepository;
    private CalendrierService calendrierService;

    @BeforeEach
    void setUp() {
        calendrierRepository = new InMemoryCalendrierRepositoryTest();
        calendrierService = new CalendrierService(calendrierRepository);
    }

    @Nested
    class SuccessWhen {

        @Test
        void leNouveauCreneauDoitApparaîtreDansLeCalendrierApresSonAjout() {
            // GIVEN
            CreneauDTO nouveauCreneauDTO = new CreneauDTO("12/10/2024", "10:00", "10:30");

            // WHEN
            calendrierService.ajouterCreneau(nouveauCreneauDTO);

            // THEN
            assertThat(calendrierService.recupererTousLesCreneauxDisponible().size()).isEqualTo(1);
            assertThat(calendrierService.recupererTousLesCreneauxDisponible().contains(nouveauCreneauDTO)).isTrue();
        }

    }

    @Nested
    class FailWhen {

        @Test
        void doitLeverUneExceptionSiLeCreneauxEstDejaPresent() {
            // GIVEN
            CreneauDTO creneauDTO = new CreneauDTO("12/10/2024", "10:00", "10:30");
            calendrierService.ajouterCreneau(creneauDTO);

            // WHEN
            CreneauDTO nouveauCreneauDTO = new CreneauDTO("12/10/2024", "10:00", "10:30");
            Throwable thrown = catchThrowable(() -> {
                calendrierService.ajouterCreneau(nouveauCreneauDTO);
            });

            // THEN
            assertThat(thrown)
                    .isInstanceOf(RuntimeException.class)
                    .hasMessage("Le créneau du %s de %s à %s est déjà présent dans le calendrier", nouveauCreneauDTO.getDate(), nouveauCreneauDTO.getStartTime(), nouveauCreneauDTO.getEndTime());
        }

        @Test
        void shouldThrowAnExceptionWhenTheDateIsInThePast() {

            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

            // GIVEN
            LocalDate today = LocalDate.now();
            LocalTime time = LocalTime.now();
            LocalTime startTime = LocalTime.parse(time.format(timeFormatter), timeFormatter);
            LocalTime endTime = LocalTime.parse(time.format(timeFormatter), timeFormatter).plusMinutes(30);
            CreneauDTO creneauDTO = new CreneauDTO(today.minusDays(7), startTime, endTime);


            // WHEN
            Throwable thrown = catchThrowable(() -> calendrierService.ajouterCreneau(creneauDTO));

            // THEN
            assertThat(thrown)
                    .isInstanceOf(RuntimeException.class)
                    .hasMessage("The date should be in the futur");
        }

    }

}
