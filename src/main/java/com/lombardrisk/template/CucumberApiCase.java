package com.lombardrisk.template;

import com.lombardrisk.util.TestCaseManager;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import cucumber.runtime.RuntimeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class CucumberApiCase extends CucumberApiTestTemplate {

    private TestNGCucumberRunner testNGCucumberRunner;

    @BeforeClass(alwaysRun = true)
    public void setUpClass() throws Exception {
        TestCaseManager.setTestCase(this);
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
        RuntimeOptions runtimeOptions = getRuntimeOptions(testNGCucumberRunner);
        List<String> pluginFormatterNames = getPluginFormatterNames(runtimeOptions);
        rewritePluginFormatter(pluginFormatterNames);
    }

    @Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "features")
    public void feature(CucumberFeatureWrapper cucumberFeature) {
        testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
    }

    @DataProvider
    public Object[][] features() {
        return testNGCucumberRunner.provideFeatures();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() throws Exception {
        testNGCucumberRunner.finish();
    }

}
