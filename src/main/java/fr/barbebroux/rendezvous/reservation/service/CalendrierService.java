package fr.barbebroux.rendezvous.reservation.service;

import fr.barbebroux.rendezvous.reservation.exception.CreneauDejaExistantException;
import fr.barbebroux.rendezvous.reservation.model.Creneau;
import fr.barbebroux.rendezvous.reservation.port.CalendrierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendrierService {

    private final CalendrierRepository calendrierRepository;

    @Autowired
    public CalendrierService(CalendrierRepository calendrierRepository) {
        this.calendrierRepository = calendrierRepository;
    }

    public List<Creneau> recupererTousLesCreneauxDisponible() {
        return calendrierRepository.recupererTousLesCreneauxDisponibles();
    }

    public void ajouterCreneau(Creneau creneau) {
        if(calendrierRepository.creneauExiste(creneau)) {
            throw new CreneauDejaExistantException(creneau);
        }
        calendrierRepository.ajouterCreneauDisponible(creneau);
    }
}
