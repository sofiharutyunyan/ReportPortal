Feature: Work with Daashboards

  Background:
    Given User is on Dashboards page

  Scenario Outline: Create a dashboard and make sure it is added in the dashboards list
    When User clicks on Create new Dashboard button
    And User sets dashboard "<name>"
    And User clicks on "Add" button
    Then The dashboard is created

    Examples:
      | name        |
      | Dashboard 1 |
      | Dashboard 2 |
      | Dashboard 3 |
      | Dashboard 4 |

  Scenario: Make all Dashboards Shared
    When User clicks on Edit Dashboard button
    Then The dashboards become shared

  Scenario: Clear dashboard list
    When User clicks on Create new Dashboard button
    And User sets dashboard "Must be deleted"
    And User clicks on "Add" button
    And Go back
    When User clicks on delete dashboard Icon
    Then Dashboard is deleted from dashboard list





