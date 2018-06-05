package com.lombardrisk.template;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public abstract class TestTemplate implements ITestTemplate {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    private String suiteName;
    private String testName;

    public String getSuiteName() {
        return suiteName;
    }

    public void setSuiteName(String suiteName) {
        this.suiteName = suiteName;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    @BeforeSuite
    public void beforeSuite() {
        logger.info("Start testing");
    }

    @AfterSuite
    public void afterSuite() {
        logger.info("Test ends");
    }

    public String getSuiteTestSeparator() {
        if (suiteName != null && testName != null) {
            return suiteName + "/" + testName + "/";
        }
        return "";
    }

}
