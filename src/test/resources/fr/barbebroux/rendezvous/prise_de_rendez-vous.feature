# language: fr

Fonctionnalité: Prise de rendez-vous

  Scénario: Doit voir les rendez-vous disponibles
    Etant donné un calendrier avec des créneaux disponibles
      |jour|heure|
      |2024-10-08|09:00|
      |2024-10-08|10:00|
      |2024-10-08|11:00|
    Lorsque Romain choisit le créneau "2024-10-08" à "10:00"
    Alors Le créneau "2024-10-08" à "10:00" n'est plus disponible
