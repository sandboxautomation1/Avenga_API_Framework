package org.avenga.reporting;

import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {

    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static ExtentTest getTest() {
        return test.get();
    }

    public static void startTest() {
        ExtentTest extentTest = ExtentManager.getInstance().createTest("<b>" + ExtentListener.getTestName() + "</b>",
                "<pre>"
                        + "<p align=justify>"
                        + ExtentListener.getTestDescription()
                        + "</p>"
                        + "</pre>");
        test.set(extentTest);
    }

    public static void endTest() {
        test.remove();
    }
}

