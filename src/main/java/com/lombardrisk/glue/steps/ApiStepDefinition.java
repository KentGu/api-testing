package com.lombardrisk.glue.steps;

import com.lombardrisk.template.CucumberApiCase;
import com.lombardrisk.util.TestCaseManager;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiStepDefinition {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private CucumberApiCase testCase = (CucumberApiCase) TestCaseManager.getTestCase();
    private RequestSpecification request =  testCase.getRequest();

    @Given("^set get path (\\S+)$")
    public void setBasePath(String path) {
        request = request.basePath(path);
        testCase.setRequest(request);
        logger.info("set path " + path);
    }

    @When("^get$")
    public void get() {
        testCase.setResponse(request.get());
    }

    @Then("^status code is (\\d+)$")
    public void checkStatus(String code) {
        testCase.getResponse().then().statusCode(Integer.parseInt(code));
    }
}
