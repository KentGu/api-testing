package com.lombardrisk.util;

import com.lombardrisk.template.ITestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestCaseManager {
    private static final Logger logger = LoggerFactory.getLogger(TestCaseManager.class);
    private static ThreadLocal<ITestTemplate> testCase = new ThreadLocal<ITestTemplate>();

    public synchronized static ITestTemplate getTestCase() {
        return testCase.get();
    }

    public synchronized static void setTestCase(ITestTemplate testCase) {
        TestCaseManager.testCase.set(testCase);
    }
}