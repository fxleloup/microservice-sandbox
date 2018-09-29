Feature: Partner Creation

  A partner should always has a name, a forename and a birthday
  He can also has a Social Security Number
  The system should provide a unique identifier for every partner/person

  Scenario: Creation without social security number
    When I create a partner with name "Do" and forename "John" born the 16.12.1981
    Then I sould receive a new partner with an attributed id

  Scenario: Creation with social security number
    When I create a partner with the following data :
      |Do  |John    |16.12.1981|1234567890 |
    Then I sould receive a new partner with an attributed id
