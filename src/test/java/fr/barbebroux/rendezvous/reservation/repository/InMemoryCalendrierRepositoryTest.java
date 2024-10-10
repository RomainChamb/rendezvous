package fr.barbebroux.rendezvous.reservation.repository;

import fr.barbebroux.rendezvous.reservation.model.Creneau;
import fr.barbebroux.rendezvous.reservation.port.CalendrierRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryCalendrierRepositoryTest implements CalendrierRepository {

    private final List<Creneau> creneaux = new ArrayList<>();

    @Override
    public List<Creneau> recupererTousLesCreneauxDisponibles() {
        return creneaux;
    }

    @Override
    public void ajouterCreneauDisponible(Creneau creneau) {
        creneaux.add(creneau);
    }

    @Override
    public boolean creneauExiste(Creneau creneau) {
        return creneaux.contains(creneau);
    }
}
