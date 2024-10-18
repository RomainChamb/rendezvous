package fr.barbebroux.rendezvous.reservation.service;

import fr.barbebroux.rendezvous.reservation.model.dto.TimeSlotDTO;
import fr.barbebroux.rendezvous.reservation.port.CalendarRepository;
import fr.barbebroux.rendezvous.reservation.repository.InMemoryCalendarRepositoryTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class CalendarServiceTest {

    private CalendarRepository calendarRepository;
    private CalendarService calendarService;

    @BeforeEach
    void setUp() {
        calendarRepository = new InMemoryCalendarRepositoryTest();
        calendarService = new CalendarService(calendarRepository);
    }

    @Nested
    class SuccessWhen {

        @Test
        void newTimeSlotShouldAppearInCalendarAfterCreation() {
            // GIVEN
            TimeSlotDTO nouveauTimeSlotDTO = new TimeSlotDTO("12/10/2024", "10:00", "10:30");

            // WHEN
            calendarService.addNewTimeSlot(nouveauTimeSlotDTO);

            // THEN
            assertThat(calendarService.fetchAllAvailableTimeSlots().size()).isEqualTo(1);
            assertThat(calendarService.fetchAllAvailableTimeSlots().contains(nouveauTimeSlotDTO)).isTrue();
        }

    }

    @Nested
    class FailWhen {

        @Test
        void shouldThrowAnExceptionIfTimeSlotIsAlreadyPresentInCalendar() {
            // GIVEN
            TimeSlotDTO timeSlotDTO = new TimeSlotDTO("12/10/2024", "10:00", "10:30");
            calendarService.addNewTimeSlot(timeSlotDTO);

            // WHEN
            TimeSlotDTO nouveauTimeSlotDTO = new TimeSlotDTO("12/10/2024", "10:00", "10:30");
            Throwable thrown = catchThrowable(() -> {
                calendarService.addNewTimeSlot(nouveauTimeSlotDTO);
            });

            // THEN
            assertThat(thrown)
                    .isInstanceOf(RuntimeException.class)
                    .hasMessage("Time slot of %s from %s to %s is already present in the calendar", nouveauTimeSlotDTO.getDate(), nouveauTimeSlotDTO.getStartTime(), nouveauTimeSlotDTO.getEndTime());
        }

        @Test
        void shouldThrowAnExceptionWhenTheDateIsInThePast() {

            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

            // GIVEN
            LocalDate today = LocalDate.now();
            LocalTime time = LocalTime.now();
            LocalTime startTime = LocalTime.parse(time.format(timeFormatter), timeFormatter);
            LocalTime endTime = LocalTime.parse(time.format(timeFormatter), timeFormatter).plusMinutes(30);
            TimeSlotDTO timeSlotDTO = new TimeSlotDTO(today.minusDays(7), startTime, endTime);


            // WHEN
            Throwable thrown = catchThrowable(() -> calendarService.addNewTimeSlot(timeSlotDTO));

            // THEN
            assertThat(thrown)
                    .isInstanceOf(RuntimeException.class)
                    .hasMessage("The date should be in the futur");
        }

    }

}
