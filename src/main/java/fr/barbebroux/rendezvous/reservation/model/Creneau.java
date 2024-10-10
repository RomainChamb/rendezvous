package fr.barbebroux.rendezvous.reservation.model;

import java.util.Objects;

public class Creneau {

    private final String date;
    private final String heureDebut;
    private final String heureFin;

    public Creneau() {
        this("","","");
    }

    public Creneau(String date, String heureDebut, String heureFin) {
        this.date = date;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Creneau creneau))
            return false;
        return Objects.equals(date, creneau.date) && Objects.equals(heureDebut, creneau.heureDebut) && Objects.equals(
                heureFin, creneau.heureFin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, heureDebut, heureFin);
    }
}
