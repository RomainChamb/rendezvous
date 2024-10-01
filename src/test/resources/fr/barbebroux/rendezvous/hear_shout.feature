Feature: Hear shout
  Scenario: Should hear sean message
    Given Lucy is 15 mètres from Sean
    When Sean shout "free cookie"
    Then Lucy hear the message

