package fr.barbebroux.rendezvous.reservation.controller;

import fr.barbebroux.rendezvous.reservation.model.dto.CreneauDTO;
import fr.barbebroux.rendezvous.reservation.service.CalendrierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CalendrierController {

    private final CalendrierService calendrierService;

    @Autowired
    public CalendrierController(CalendrierService calendrierService) {
        this.calendrierService = calendrierService;
    }

    @GetMapping("/calendrier")
    public String recupererTousLesCreneauxDisponibles(Model model) {
        model.addAttribute("creneaux", calendrierService.recupererTousLesCreneauxDisponible());
        return "calendrier";
    }

    @GetMapping("/ajouter-creneau")
    public String afficherFormulaireAjoutCreneau(Model model) {
        model.addAttribute("creneauDTO", new CreneauDTO());
        return "ajouter-creneau";
    }

    @PostMapping("/ajouter-creneau")
    public String ajouterUnCreneau(CreneauDTO creneauDTO, Model model) {
        calendrierService.ajouterCreneau(creneauDTO);
        model.addAttribute("creneau", creneauDTO);
        return "success";
    }
}
