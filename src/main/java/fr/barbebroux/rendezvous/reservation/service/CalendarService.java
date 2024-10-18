package fr.barbebroux.rendezvous.reservation.service;

import fr.barbebroux.rendezvous.reservation.model.TimeSlot;
import fr.barbebroux.rendezvous.reservation.model.dto.TimeSlotDTO;
import fr.barbebroux.rendezvous.reservation.port.CalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CalendarService {

    private final CalendarRepository calendarRepository;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Autowired
    public CalendarService(CalendarRepository calendarRepository) {
        this.calendarRepository = calendarRepository;
    }

    public List<TimeSlotDTO> fetchAllAvailableTimeSlots() {
        return calendarRepository.fetchAllAvailableTimeSlots().stream().map(timeSlot -> new TimeSlotDTO(timeSlot.date(),timeSlot.startTime(), timeSlot.endTime())).collect(Collectors.toList());
    }

    public void addNewTimeSlot(TimeSlotDTO timeSlotDTO) {
        validateChronology(timeSlotDTO);
        TimeSlot timeSlot = new TimeSlot(timeSlotDTO.getDate(), timeSlotDTO.getStartTime(), timeSlotDTO.getEndTime());
        checkExistence(timeSlot);
        calendarRepository.addNewTimeSlot(timeSlot);
    }

    public void bookAppointment(TimeSlotDTO availableTimeSlot) {
        TimeSlot timeSlotToBook = new TimeSlot(availableTimeSlot.getDate(), availableTimeSlot.getStartTime(), availableTimeSlot.getEndTime());
        calendarRepository.bookAppointment(timeSlotToBook);
    }

    private void validateChronology(TimeSlotDTO timeSlotDTO) {
        LocalDate today = LocalDate.now();
        if(today.isAfter(LocalDate.parse(timeSlotDTO.getDate(), formatter))) {
            throw new RuntimeException("The date should be in the futur");
        }
    }

    private void checkExistence(TimeSlot timeSlot) {
        if(calendarRepository.creneauExiste(timeSlot)) {
            throw new RuntimeException(formateMessageAllReadyExistingTimeSlot(timeSlot));
        }
    }

    private String formateMessageAllReadyExistingTimeSlot(TimeSlot timeSlot) {
        return String.format("Time slot of %s from %s to %s is already present in the calendar", timeSlot.date().format(formatter),
                timeSlot.startTime(), timeSlot.endTime());
    }
}
