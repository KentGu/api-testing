Feature: test feature
  Background: 
    Given set get path /api
    
    Scenario: test Scenario
      When get
      Then status code is 404