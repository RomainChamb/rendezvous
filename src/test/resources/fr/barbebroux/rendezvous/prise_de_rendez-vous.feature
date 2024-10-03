# language: fr

Fonctionnalité: Prise de rendez-vous

  Scénario: Une fois le rendez-vous pris, il n'est plus visible
    Etant donné un calendrier avec des créneaux disponibles
      |jour|heure|
      |2024-10-08|09:00|
      |2024-10-08|10:00|
      |2024-10-08|11:00|
    Lorsque Romain choisit le créneau "2024-10-08" à "10:00"
    Alors Le créneau "2024-10-08" à "10:00" n'est plus disponible

  Scénario: Un rendez-vous pris doit apparaître dans le calendrier du patient
    Etant donné un calendrier avec des créneaux disponibles
      |jour|heure|
      |2024-10-08|09:00|
      |2024-10-08|10:00|
      |2024-10-08|11:00|
    Lorsque Romain choisit le créneau "2024-10-08" à "11:00"
    Alors Le calendier de Romain contient un rendez-vous le "2024-10-08" à "11:00"
