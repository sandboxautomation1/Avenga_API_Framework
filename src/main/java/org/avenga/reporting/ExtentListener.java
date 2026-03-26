package org.avenga.reporting;

import lombok.Getter;
import lombok.Setter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;

public class ExtentListener implements ITestListener {

    @Getter
    @Setter
    private static String testName;

    @Getter
    @Setter
    private static String testDescription;


    @Override
    public void onStart(ITestContext context) {
    }

    @Override
    public void onFinish(ITestContext context) {
    }

    @Override
    public void onTestStart(ITestResult result) {
        Test testAnnotation = result.getMethod()
                .getConstructorOrMethod()
                .getMethod()
                .getAnnotation(Test.class);

        setTestName(testAnnotation.testName());
        setTestDescription(result.getMethod().getDescription());

        if (testName == null || testName.isEmpty()) {
            testName = result.getMethod().getMethodName(); // fallback
        }
        System.out.println(testName);

        ExtentTestManager.startTest();
        String[] groups = result.getMethod().getGroups();
        String category = groups[0];
        ExtentTestManager.getTest().assignCategory(category);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTestManager.getTest().createNode("Request").info("<pre><center><b>* * * * * * * *    R E Q U E S T    * * * * * * * *</b></center></br></br>" + WriterOutputStream.getRequestLog() + "</br></pre>");
        ExtentTestManager.getTest().createNode("Response").pass("<pre><center><b>* * * * * * * *    R E S P O N S E    * * * * * * * *</b></center></br></br>" + WriterOutputStream.getResponseLog() + "</br></pre>");

        }


    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTestManager.getTest().createNode("Request").info("<pre><center><b>* * * * * * * *    R E Q U E S T    * * * * * * * *</b></center></br></br>" + WriterOutputStream.getRequestLog() + "</br></pre>");
        ExtentTestManager.getTest().createNode("Response").fail("<pre><center><b>* * * * * * * *    R E S P O N S E    * * * * * * * *</b></center></br></br>" + WriterOutputStream.getResponseLog() + "</br>" + result.getThrowable().getMessage() + "</br></pre>");

        }


    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTestManager.getTest().skip("Test skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // not commonly used
    }
}

