package com.lombardrisk.glue.hooks;

import com.lombardrisk.template.CucumberApiCase;
import com.lombardrisk.util.TestCaseManager;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CucumberHooks {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private CucumberApiCase testCase;

    @Before
    public void beforeScenario(Scenario scenario) {
        if (TestCaseManager.getTestCase() == null)
            TestCaseManager.setTestCase(new CucumberApiCase());
        testCase = (CucumberApiCase) TestCaseManager.getTestCase();
        testCase.setScenario(scenario);
        testCase.setFeatureId(scenario.getId().substring(0, scenario.getId().indexOf(";")));
        testCase.setScenarioId(scenario.getId());
        testCase.setupTest();
    }

    @After
    public void afterScenario(Scenario scenario) {
        logger.info(scenario.getStatus());
    }
}
