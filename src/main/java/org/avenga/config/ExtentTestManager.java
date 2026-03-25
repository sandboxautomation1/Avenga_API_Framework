package org.avenga.config;

import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {

    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static ExtentTest getTest() {
        return test.get();
    }

    public static void startTest() {
        ExtentTest extentTest = ExtentManager.getInstance().createTest("<b>" + ExtentListener.getTestName() + "</b>",
                "<pre>"
                        + "<center><b>* * * * * * * *    I N F O R M A T I O N    * * * * * * * *</b></center>"
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

