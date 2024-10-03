package fr.barbebroux.rendezvous.reservation.model;

import java.util.Objects;

public class Creneau {

    private final String date;
    private final String heure;

    public Creneau(String date, String heure) {
        this.date = date;
        this.heure = heure;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Creneau creneau = (Creneau) o;
        return Objects.equals(date, creneau.date) && Objects.equals(heure, creneau.heure);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, heure);
    }
}
