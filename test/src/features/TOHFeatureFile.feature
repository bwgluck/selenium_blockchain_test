@bgdebug
Feature: TOH test showcase

  Scenario: [1] Observe the Tour of Heroes dashboard
    Given I navigate to the URL "localhost:4200"
    Then I see the TOH page title header
    Then I see the TOH page navigation
    Then I see the top heroes section
    Then I see the hero search section
    Then I see the message history section
    Then I close the browser
    
  Scenario: [2] Check functionality of hero details
    Given I navigate to the URL "localhost:4200"
    Then I close the browser
    
  Scenario: [3] Check functionality of hero search
    Given I navigate to the URL "localhost:4200"
    Then I close the browser
    
  Scenario: [4] Check functionality of my heroes page
    Given I navigate to the URL "localhost:4200"
    Then I close the browser
    
  Scenario: [5] Check functionality of message history component
    Given I navigate to the URL "localhost:4200"
    Then I close the browser
