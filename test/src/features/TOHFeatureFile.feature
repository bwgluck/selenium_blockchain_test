@bg @TOHFeature 
Feature: TOH test showcase 

Scenario: [1] Observe the Tour of Heroes dashboard 
	Given I navigate to the URL "localhost:4200" 
	Then I see the TOH page title header 
	Then I see the TOH page navigation 
	Then I see the top heroes section 
	Then I see the hero search section 
	Then I see the message history section 
	Then I see message entry "0" with text "HeroService: fetched heroes" 
	Then I close the browser 
	
Scenario: [2] Check functionality of hero details page 
	Given I navigate to the URL "localhost:4200" 
	Then I clear the message history and verify it has been erased 
	Then I click and view the details for top hero "Narco" 
	Then I see message entry "0" with text "HeroService: fetched hero id=12" 
	Then I go back to the dashboard from hero details page 
	Then I see the top heroes section 
	Then I click and view the details for top hero "Bombasto" 
	Then I clear the message history and verify it has been erased 
	Then I edit hero name and save as "Bombastone" 
	Then I see the top heroes section 
	Then I see this hero name in top heroes: "Bombastone" 
	Then I see message entry "0" with text "HeroService: updated hero id=13" 
	Then I close the browser 
	
Scenario: [3] Check functionality of hero search 
	Given I navigate to the URL "localhost:4200" 
	Then I see the hero search section 
	Then I search for hero name "Narf" 
	Then I see message entry "1" with text "HeroService: no heroes matching 'Narf'" 
	Then I clear the message history and verify it has been erased 
	Then I search for hero name "Celeritas" and click on the result 
	Then I view the details for "Celeritas" 
	Then I see message entry "0" with text "HeroService: found heroes matching 'Celeritas'" 
	Then I see message entry "1" with text "HeroService: fetched hero id=14" 
	Then I close the browser 
	
Scenario: [4] Check functionality of my heroes page 
	Given I navigate to the URL "localhost:4200" 
	Then I clear the message history and verify it has been erased 
	Then I see the TOH page navigation 
	Then I click on the my heroes navigation button 
	Then I see message entry "0" with text "HeroService: fetched heroes" 
	Then I see my heroes elements 
	Then I add hero "Etrigan" to the heroes list 
	Then I see message entry "1" with text "HeroService: added hero w/ id=21" 
	Then I see "Etrigan" in the heroes list and click the entry 
	Then I view the details for "Etrigan" 
	Then I go back to my heroes from hero details page 
	Then I see my heroes elements 
	Then I clear the message history and verify it has been erased 
	Then I delete "Etrigan" from the heroes list 
	Then I see message entry "0" with text "HeroService: deleted hero id=21" 
	Then I do not see "Etrigan" in the heroes list 
	Then I close the browser 
	