package org.avenga.base;

import org.avenga.reporting.ExtentManager;
import org.avenga.utils.FileUtils;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class BaseTest {


    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() throws IOException {
        FileUtils.deleteZipFile();
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() throws Exception {
        ExtentManager.getInstance().flush();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDownReport() throws IOException {
        try {
            Files.createDirectories(Paths.get("test-output-archive"));
        } catch (Exception exception) {
            System.out.println("test-output-archive directory already exists");
        }

        String folder = "test-output";
        String file   = "test-output-archive/Automation_Report.zip";
        FileUtils.createZipFile(folder, file);

        org.apache.commons.io.FileUtils.forceMkdir(new File(folder));
    }
}
