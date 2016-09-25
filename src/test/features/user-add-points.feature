Feature: User has the ability to add points to him

  Scenario Outline: Points that are added to the user exists are assigned to his account
    Given User has "<initial_points>" points at his account
    When User gets the "<points_to_add>" points added
    Then User has "<new_points_amount>"
    Examples:
      | initial_points | points_to_add | new_points_amount |
      | 0              | 100           | 100               |
      | 100            | 0             | 100               |
      | 100            | 100           | 200               |

