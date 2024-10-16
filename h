[1mdiff --git a/src/main/java/fr/barbebroux/rendezvous/reservation/port/Calendrier.java b/src/main/java/fr/barbebroux/rendezvous/reservation/port/Calendrier.java[m
[1mindex bd8e801..4fd5a39 100644[m
[1m--- a/src/main/java/fr/barbebroux/rendezvous/reservation/port/Calendrier.java[m
[1m+++ b/src/main/java/fr/barbebroux/rendezvous/reservation/port/Calendrier.java[m
[36m@@ -11,4 +11,6 @@[m [mpublic interface Calendrier {[m
     boolean reserverRendezVous(Creneau timeSlot);[m
 [m
     boolean isCreneauDisponible(Creneau timeSlot);[m
[32m+[m
[32m+[m[32m    boolean isCreneauReserve(String nomDuPatient, Creneau timeSlot);[m
 }[m
[1mdiff --git a/src/main/java/fr/barbebroux/rendezvous/reservation/repository/InMemoryCalendrier.java b/src/main/java/fr/barbebroux/rendezvous/reservation/repository/InMemoryCalendrier.java[m
[1mindex c52a0fc..db220e3 100644[m
[1m--- a/src/main/java/fr/barbebroux/rendezvous/reservation/repository/InMemoryCalendrier.java[m
[1m+++ b/src/main/java/fr/barbebroux/rendezvous/reservation/repository/InMemoryCalendrier.java[m
[36m@@ -40,4 +40,9 @@[m [mpublic class InMemoryCalendrier implements Calendrier {[m
     public boolean isCreneauDisponible(Creneau timeSlot) {[m
         return creneaux.contains(timeSlot);[m
     }[m
[32m+[m
[32m+[m[32m    @Override[m
[32m+[m[32m    public boolean isCreneauReserve(String nomDuPatient, Creneau timeSlot) {[m
[32m+[m[32m        return false;[m
[32m+[m[32m    }[m
 }[m
[1mdiff --git a/src/main/java/fr/barbebroux/rendezvous/reservation/service/Reservation.java b/src/main/java/fr/barbebroux/rendezvous/reservation/service/Reservation.java[m
[1mindex a6bb337..7d6527f 100644[m
[1m--- a/src/main/java/fr/barbebroux/rendezvous/reservation/service/Reservation.java[m
[1m+++ b/src/main/java/fr/barbebroux/rendezvous/reservation/service/Reservation.java[m
[36m@@ -26,4 +26,8 @@[m [mpublic class Reservation {[m
     public boolean isCreneauDisponible(Creneau timeSlot) {[m
         return calendrier.isCreneauDisponible(timeSlot);[m
     }[m
[32m+[m
[32m+[m[32m    public boolean isCreneauReserve(String nomDuPatient, Creneau timeSlot) {[m
[32m+[m[32m        return calendrier.isCreneauReserve(nomDuPatient, timeSlot);[m
[32m+[m[32m    }[m
 }[m
[1mdiff --git a/src/test/java/fr/barbebroux/rendezvous/bdd/PriseDeRendezVousSteps.java b/src/test/java/fr/barbebroux/rendezvous/bdd/PriseDeRendezVousSteps.java[m
[1mindex 7655f2f..5b20469 100644[m
[1m--- a/src/test/java/fr/barbebroux/rendezvous/bdd/PriseDeRendezVousSteps.java[m
[1m+++ b/src/test/java/fr/barbebroux/rendezvous/bdd/PriseDeRendezVousSteps.java[m
[36m@@ -54,8 +54,11 @@[m [mpublic class PriseDeRendezVousSteps {[m
         assertThat(reservation.isCreneauDisponible(timeSlot)).isFalse();[m
     }[m
 [m
[31m-    @Alors("Le calendier de Romain contient un rendez-vous le {string} à {string}")[m
[31m-    public void leCalendierDeRomainContientUnRendezVousLeÀ(String date, String heure) {[m
[32m+[m[32m    @Alors("Le calendier de {word} contient un rendez-vous le {string} à {string}")[m
[32m+[m[32m    public void leCalendierDeRomainContientUnRendezVousLeÀ(String nomDuPatient, String date, String heure) {[m
[32m+[m[32m        Creneau timeSlot = new Creneau(date, heure);[m
[32m+[m[32m        reservation.prendreRendezVous(timeSlot);[m
[32m+[m[32m        assertThat(reservation.isCreneauReserve(nomDuPatient,timeSlot)).isTrue();[m
         throw new PendingException();[m
     }[m
 }[m
[1mdiff --git a/src/test/java/fr/barbebroux/rendezvous/reservation/ReservationTest.java b/src/test/java/fr/barbebroux/rendezvous/reservation/ReservationTest.java[m
[1mindex 1a49061..6e9101c 100644[m
[1m--- a/src/test/java/fr/barbebroux/rendezvous/reservation/ReservationTest.java[m
[1m+++ b/src/test/java/fr/barbebroux/rendezvous/reservation/ReservationTest.java[m
[36m@@ -54,5 +54,4 @@[m [mclass ReservationTest {[m
         // THEN[m
         assertThat(reservation.prendreRendezVous(timeSlot)).isFalse();[m
     }[m
[31m-[m
 }[m
