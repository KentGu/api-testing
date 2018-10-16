package com.lombardrisk.glue.steps;

import com.lombardrisk.template.CucumberApiCase;
import com.lombardrisk.util.TestCaseManager;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.not;

public class ApiStepDefinition {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private CucumberApiCase testCase = (CucumberApiCase) TestCaseManager.getTestCase();
    private RequestSpecification request = testCase.getRequest();

    @Given("^set get path (\\S+)$")
    public void setBasePath(String path) {
        request.basePath(path);
//        testCase.setRequest(request);
        logger.info("set path " + path);
    }

    @When("^set credential$")
    public void setCredential() {
        request.auth().basic("qtptest", "1");;
//        testCase.setRequest(request);
    }

    @When("^get$")
    public void get() {
        testCase.setResponse(request.get());
    }

    @When("^get (\\S+)$")
    public void get(String path) {
        testCase.setResponse(request.get(path));
    }

    @Then("^status code is (\\d+)$")
    public void checkStatus(String code) {
        testCase.getResponse().then().statusCode(Integer.parseInt(code));
    }

    @Then("^response body")
    public void checkBody() {
        testCase.getResponse().then().body("executions.id", hasItems(5514, 551));
        testCase.getResponse().then().body("executions.id", not(hasItems(0)));
    }

    @Then("^interest response body")
    public void interest_checkBody() {
        testCase.getResponse().then().body("executions.id", hasItems(21513, 21514));
        testCase.getResponse().then().body("executions.id", not(hasItems(0)));
    }
}
