package org.avenga.base;

import org.avenga.reporting.ExtentListener;
import org.avenga.reporting.ExtentManager;
import org.avenga.reporting.ExtentTestManager;
import org.avenga.utils.FileUtils;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.avenga.reporting.ExtentListener.testCategory;
import static org.avenga.reporting.ExtentListener.testName;


public class BaseTest {


    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() throws IOException {
        FileUtils.deleteZipFile();
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() throws Exception {
        ExtentManager.getInstance().flush();
    }
}
