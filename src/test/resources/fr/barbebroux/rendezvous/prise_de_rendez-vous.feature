# language: fr

Fonctionnalité: Prise de rendez-vous

  Scénario: Doit voir les rendez-vous disponibles
    Etant donné un calendrier avec des créneaux disponibles
    |2024-10-08T09:00|
    |2024-10-08T10:00|
    |2024-10-08T11:00|
    Lorsque Romain choisit le créneau "2024-10-08T10:00"
    Alors Le créneau "2024-10-08T10:00" n'est plus disponible
