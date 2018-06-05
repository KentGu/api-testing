package com.lombardrisk.template;

import com.lombardrisk.util.PropertiesHandler;
import cucumber.api.Scenario;
import cucumber.api.testng.TestNGCucumberRunner;
import cucumber.runtime.RuntimeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;

import static io.restassured.RestAssured.given;

public abstract class CucumberApiTestTemplate extends ApiTestTemplate {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private Scenario scenario;
    private String scenarioId;
    private String featureId;

    public Scenario getScenario() {
        return scenario;
    }

    public void setScenario(Scenario scenario) {
        logger.info("Set scenario: " + scenario.toString());
        this.scenario = scenario;
    }

    protected RuntimeOptions getRuntimeOptions(TestNGCucumberRunner testNGCucumberRunner) throws NoSuchFieldException, IllegalAccessException {
        Field field = TestNGCucumberRunner.class.getDeclaredField("runtimeOptions");
        field.setAccessible(true);
        return (RuntimeOptions) field.get(testNGCucumberRunner);
    }

    protected List<String> getPluginFormatterNames(RuntimeOptions runtimeOptions) throws NoSuchFieldException, IllegalAccessException {
        Field field = RuntimeOptions.class.getDeclaredField("pluginFormatterNames");
        field.setAccessible(true);
        return (List<String>) field.get(runtimeOptions);
    }

    protected synchronized void rewritePluginFormatter(List<String> pluginFormatterNames) {
        String[] formats = {"json", "html", "rerun"};
        String pluginFormatterName;
        for (int i = 0; i < pluginFormatterNames.size(); i++) {
            for (String format : formats) {
                if ((pluginFormatterName = pluginFormatterNames.get(i).trim()).startsWith(format)) {
                    String path = pluginFormatterName.substring((format + ":").length());
                    File file = new File(path);
                    String newPath = file.getParent() + "/" + getSuiteTestSeparator() + file.getName();
                    pluginFormatterNames.set(i, format + ":" + newPath);
                }
            }
        }
    }

    public String getScenarioId() {
        return scenarioId;
    }

    public void setScenarioId(String scenarioId) {
        this.scenarioId = scenarioId;
    }

    public String getFeatureId() {
        return featureId;
    }

    public void setFeatureId(String featureId) {
        this.featureId = featureId;
    }

    public void setupTest() {
        setRequest(given().baseUri(PropertiesHandler.getProperty("baseUri")));
        getRequest().urlEncodingEnabled(Boolean.getBoolean(PropertiesHandler.getProperty("urlEncodeingEnabled")));
    }
}
