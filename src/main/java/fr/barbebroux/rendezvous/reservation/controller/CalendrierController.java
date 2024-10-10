package fr.barbebroux.rendezvous.reservation.controller;

import fr.barbebroux.rendezvous.reservation.dto.CreneauDTO;
import fr.barbebroux.rendezvous.reservation.model.Creneau;
import fr.barbebroux.rendezvous.reservation.service.CalendrierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.List;

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
        // Initialiser un nouvel objet Creneau vide
        model.addAttribute("creneauDTO", new CreneauDTO());
        return "ajouter-creneau";
    }

    @PostMapping("/ajouter-creneau")
    public String ajouterUnCreneau(CreneauDTO creneauDTO, Model model) {
        Creneau creneau = new Creneau(creneauDTO.getDate(), creneauDTO.getHeureDebut(), creneauDTO.getHeureFin());
        calendrierService.ajouterCreneau(creneau);
        model.addAttribute("creneau", creneauDTO);
        return "success";
    }
}
