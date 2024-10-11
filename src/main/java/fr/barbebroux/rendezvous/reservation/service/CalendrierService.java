package fr.barbebroux.rendezvous.reservation.service;

import fr.barbebroux.rendezvous.reservation.model.dto.CreneauDTO;
import fr.barbebroux.rendezvous.reservation.model.Creneau;
import fr.barbebroux.rendezvous.reservation.port.CalendrierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CalendrierService {

    private final CalendrierRepository calendrierRepository;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Autowired
    public CalendrierService(CalendrierRepository calendrierRepository) {
        this.calendrierRepository = calendrierRepository;
    }

    public List<CreneauDTO> recupererTousLesCreneauxDisponible() {
        return calendrierRepository.recupererTousLesCreneauxDisponibles().stream().map(creneau -> new CreneauDTO(creneau.date(),creneau.startTime(), creneau.endTime())).collect(Collectors.toList());
    }

    public void ajouterCreneau(CreneauDTO creneauDTO) {
        validateChronology(creneauDTO);
        Creneau creneau = new Creneau(creneauDTO.getDate(), creneauDTO.getStartTime(), creneauDTO.getEndTime());
        checkExistence(creneau);
        calendrierRepository.ajouterCreneauDisponible(creneau);
    }

    private void validateChronology(CreneauDTO creneauDTO) {
        LocalDate today = LocalDate.now();
        if(today.isAfter(LocalDate.parse(creneauDTO.getDate(), formatter))) {
            throw new RuntimeException("The date should be in the futur");
        }
    }

    private void checkExistence(Creneau creneau) {
        if(calendrierRepository.creneauExiste(creneau)) {
            throw new RuntimeException(formateMessageCreneauDejaExistant(creneau));
        }
    }

    private String formateMessageCreneauDejaExistant(Creneau creneau) {
        return String.format("Le créneau du %s de %s à %s est déjà présent dans le calendrier", creneau.date().format(formatter),
                creneau.startTime(), creneau.endTime());
    }
}
