package fr.barbebroux.rendezvous.reservation.repository;

import fr.barbebroux.rendezvous.reservation.model.Creneau;
import fr.barbebroux.rendezvous.reservation.port.CalendrierRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class InMemoryCalendrierRepository implements CalendrierRepository {

    private final List<Creneau> creneaux = Stream.of(
            new Creneau("10/10/2024", "10:00", "10:30"),
            new Creneau("10/10/2024", "10:30", "11:00"),
            new Creneau("11/10/2024", "11:00", "11:30"),
            new Creneau("11/10/2024", "14:00", "14:30")).collect(Collectors.toList());


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
