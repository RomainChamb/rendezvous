package fr.barbebroux.rendezvous.reservation.model;

import java.util.Objects;

public class Creneau {

    private final String date;

    public Creneau(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Creneau creneau = (Creneau) o;
        return Objects.equals(date, creneau.date);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(date);
    }
}
