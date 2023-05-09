Feature: Work with Daashboards

  Background:
  Given User is on Dashboards page

  Scenario Outline: Create a dashboard and make sure it is added in the dashboards list
    When User clicks on Create new Dashboard button
    And User sets dashboard "<name>"
    And User clicks on "Add" button
    Then The dashboard is created

    Examples:
      | name  |
      | Demo7 |
      | Demo8 |
      | Demo9 |
      | Demo0 |

    Scenario: Make Dashboards Shared
      When User clicks on Edit Dashboard button
      Then The dashboards become shared






