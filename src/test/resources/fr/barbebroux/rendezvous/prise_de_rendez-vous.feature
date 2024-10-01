# language: fr

Fonctionnalité: Prise de rendez-vous

  Scénario: Doit voir les rendez-vous disponibles
    Etant donné que Blandine a définit 3 créneaux disponibles
    Lorsque Romain choisit un créneaux
    Alors Le créneau ne doit plus apparaître dans la liste
