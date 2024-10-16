package fr.barbebroux.rendezvous.reservation.controller;

import fr.barbebroux.rendezvous.reservation.model.dto.TimeSlotDTO;
import fr.barbebroux.rendezvous.reservation.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CalendarController {

    private final CalendarService calendarService;

    @Autowired
    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @GetMapping("/calendar")
    public String recupererTousLesCreneauxDisponibles(Model model) {
        model.addAttribute("timeSlots", calendarService.recupererTousLesCreneauxDisponible());
        return "calendar";
    }

    @GetMapping("/add-timeSlot")
    public String afficherFormulaireAjoutCreneau(Model model) {
        model.addAttribute("timeSlotDTO", new TimeSlotDTO());
        return "/add-timeSlot";
    }

    @PostMapping("/add-timeSlot")
    public String ajouterUnCreneau(TimeSlotDTO timeSlotDTO, Model model) {
        calendarService.ajouterCreneau(timeSlotDTO);
        model.addAttribute("timeSlot", timeSlotDTO);
        return "success";
    }
}
