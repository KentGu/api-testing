Feature: test feature
  Background: 
    Given set get path /colline-web-service/services/taskmanager
    
    Scenario: test Scenario
      When set credential
      And get /organisationfeed
      Then status code is 200
      And response body