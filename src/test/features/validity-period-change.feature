Feature: Ability to change Validty Period by user
  User that has an abonament for a Game, has the ability to change the Validity Period
  of that Game with all consequences:
    - payment generation/removal
    - bank of points update
    - availability of a Game on that period only


  Scenario: User changes the start point of Game back in the past
    Given 1 user has initially 400 points
    And User owns 198 Game
    And 198 Game cost for one month is 120
    And 198 Game is available between "2016-04" and "2016-06"
    When 1 User extends Validity Period for 198 Game up to "2016-02" and "2016-06"
    Then Validity Period for 198 Game stands for "2016-02" and "2016-06"
    Then 1 user has 260 points
    Then 1 user has 2 payments that are generated with price 120 each

