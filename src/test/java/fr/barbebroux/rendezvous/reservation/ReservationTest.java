package fr.barbebroux.rendezvous.reservation;

import fr.barbebroux.rendezvous.reservation.model.Creneau;
import fr.barbebroux.rendezvous.reservation.repository.InMemoryCalendrier;
import fr.barbebroux.rendezvous.reservation.service.Reservation;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ReservationTest {

    @Test
    void doitRetournerFalseQuandLeCreneauNestPasDisponible() {
        // GIVEN
        Reservation reservation = new Reservation(new InMemoryCalendrier());
        Creneau creneauDisponible = new Creneau("2024-10-02", "08:00");
        reservation.ajouterUnCreneauDisponible(creneauDisponible);

        // WHEN
        Creneau creneau = new Creneau("2024-10-05", "10:00");

        // THEN
        assertThat(reservation.prendreRendezVous(creneau)).isFalse();
    }

    @Test
    void doitRetournerTrueQuandLeCreneauEstDisponible() {
        // GIVEN
        Reservation reservation = new Reservation(new InMemoryCalendrier());
        Creneau creneauDisponible = new Creneau("2024-10-02", "08:00");
        reservation.ajouterUnCreneauDisponible(creneauDisponible);

        // WHEN
        Creneau creneau = new Creneau("2024-10-02","08:00");

        // THEN
        assertThat(reservation.prendreRendezVous(creneau)).isTrue();
    }

    @Test
    void doitRetournerFalseQuandLeCreneauAEteReserve() {
        // GIVEN
        Reservation reservation = new Reservation(new InMemoryCalendrier());
        reservation.ajouterUnCreneauDisponible(new Creneau("2024-10-02","08:00"));
        reservation.ajouterUnCreneauDisponible(new Creneau("2024-10-02", "09:00"));
        reservation.ajouterUnCreneauDisponible(new Creneau("2024-10-02","010:00"));

        // WHEN
        Creneau creneau = new Creneau("2024-10-02","09:00");
        reservation.prendreRendezVous(creneau);

        // THEN
        assertThat(reservation.prendreRendezVous(creneau)).isFalse();
    }

}
