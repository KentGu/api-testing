package com.lombardrisk.template;

public interface ITestTemplate {
    String getSuiteName();

    void setSuiteName(String suiteName);

    String getTestName();

    void setTestName(String testName);

    String getSuiteTestSeparator();

    void setupTest();
}
