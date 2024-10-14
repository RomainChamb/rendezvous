package fr.barbebroux.rendezvous.reservation.model;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

class CreneauTest {

    @Nested
    class SuccessWhen {

        @Test
        void shouldCreateANewCreneau() {
            // GIVEN
            String date = "20/10/2024";
            String startTime = "10:00";
            String endTime = "11:00";

            // WHEN
            Creneau creneau = new Creneau(date, startTime, endTime);

            // THEN
            assertThat(creneau).isNotNull();
            assertThat(creneau.date()).isEqualTo(LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            assertThat(creneau.startTime()).isEqualTo(startTime);
            assertThat(creneau.endTime()).isEqualTo(endTime);
        }
    }

    @Nested
    class FailWhen {

        @Test
        void shouldThrowAnExceptionWhenDateIsWronglyFormatted() {
            // GIVEN
            String date = "20-10-2024";
            String heureDebut = "10:00";
            String heureFin = "11:00";

            // WHEN
            Throwable thrown = catchThrowable( () -> new Creneau(date, heureDebut, heureFin));

            // THEN
            assertThat(thrown)
                    .isInstanceOf(DateTimeParseException.class)
                    .hasMessageContaining("could not be parsed at index");
        }

        @Test
        void shouldThrowAnExceptionWhenStartTimeIsWronglyFormatted() {
            // GIVEN
            String date = "20/10/2024";
            String startTime = "10h40";
            String heureFin = "11:00";

            // WHEN
            Throwable thrown = catchThrowable( () -> new Creneau(date, startTime, heureFin));

            // THEN
            assertThat(thrown)
                    .isInstanceOf(DateTimeParseException.class)
                    .hasMessageContaining("could not be parsed at index");
        }

        @Test
        void shouldThrowAnExceptionWhenEndTimeIsWronglyFormatted() {
            // GIVEN
            String date = "20/10/2024";
            String startTime = "10:00";
            String endTime = "11H00";

            // WHEN
            Throwable thrown = catchThrowable( () -> new Creneau(date, startTime, endTime));

            // THEN
            assertThat(thrown)
                    .isInstanceOf(DateTimeParseException.class)
                    .hasMessageContaining("could not be parsed at index");
        }

        @Test
        void shouldThrowAnExceptionWhenEndTimeIsBeforeStartTime() {
            // GIVEN
            String date = "20/10/2024";
            String startTime = "10:00";
            String endTime = "09:00";

            // WHEN
            Throwable thrown = catchThrowable( () -> new Creneau(date, startTime, endTime));

            // THEN
            assertThat(thrown)
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("The endTime should be after the startTime");
        }

        @Test
        void shouldThrowAnExceptionWhenEndTimeEgalsStartTime() {
            // GIVEN
            String date = "20/10/2024";
            String startTime = "10:00";
            String endTime = "10:00";

            // WHEN
            Throwable thrown = catchThrowable( () -> new Creneau(date, startTime, endTime));

            // THEN
            assertThat(thrown).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("The endTime should be after the startTime");
        }
    }

}
