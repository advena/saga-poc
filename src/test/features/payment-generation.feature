Feature: Payments are generated for desired date range with given price

  Scenario Outline: Payments are generated for given date range
    Given list of "<months>"
    When payments are generated with "<price>"
    Then should "<number>" of payments be generated
    Then each payment matches month when is generated
    Then each payment matches "<price>"
    Examples:
      | months                 | price | number |
      | 2016-01-01             | 100   | 1      |
      | 2016-01-01, 2016-01-02 | 100   | 2      |
