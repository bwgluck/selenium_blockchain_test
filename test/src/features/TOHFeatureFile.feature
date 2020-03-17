Feature: TOH test showcase 

Scenario: [1] Observe the Tour of Heroes dashboard 
	Given I navigate to the URL "localhost:4200"
	Then I see the TOH page title header
	Then I see the TOH page navigation
	Then I see the top heroes section
	Then I see the hero search section
	Then I see the message history section
	Then I close the browser
	
@bgdebug 
Scenario: [2] Check functionality of hero details page
	Given I navigate to the URL "localhost:4200"
	Then I click and view the details for top hero "Narco"
	Then I go back to the dashboard from hero details page
	Then I see the top heroes section
	Then I click and view the details for top hero "Bombasto"
	Then I edit hero name and save as "Bombastone"
	Then I see the top heroes section
	Then I see this hero name in top heroes: "Bombastone"
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
