Feature: Validity Period can be extended in time with change of start and end month

  Scenario Outline: Validity Period changes when start period is changed
    Given Validity Period begins "<start_month>" and ends "<end_month>"
    When Validity Period start is changed to "<new_start_month>" and "<new_end_month>"
    Then Validity Period should be available in "<availability_months>"
    Examples:
      | start_month | end_month | new_start_month | new_end_month | availability_months                                                                                                                                        |
      | "2016-01"   | "2016-01" | "2016-01"       | "2016-01"     | ["2016-01"]                                                                                                                                                |
      | "2016-03"   | "2016-05" | "2016-01"       | "2016-05"     | ["2016-01", "2016-02", "2016-03", "2016-04", "2016-05"]                                                                                                    |
      | "2016-01"   | "2016-02" | "2015-12"       | "2016-02"     | ["2015-12", "2016-01", "2016-02"]                                                                                                                          |
      | "2016-11"   | "2016-12" | "2016-11"       | "2017-01"     | ["2016-11", "2016-12", "2017-01"]                                                                                                                          |
      | "2016-01"   | "2016-12" | "2015-12"       | "2017-01"     | ["2015-12", "2016-01", "2016-02", "2016-03", "2016-04", "2016-05", "2016-06", "2016-07", "2016-08", "2016-09", "2016-10", "2016-11", "2016-12", "2017-01"] |


