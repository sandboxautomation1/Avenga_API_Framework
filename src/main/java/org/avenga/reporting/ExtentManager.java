package org.avenga.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.ViewName;

import java.io.File;

import static org.avenga.data.FrameworkConstants.REPORT_HTML_FILE;
import static org.avenga.data.FrameworkConstants.REPORT_LOGO_FILE;

public class ExtentManager {


    private static ExtentReports extent;

    public static ExtentReports getInstance() {

        if (extent == null) {
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(REPORT_HTML_FILE).
            viewConfigurer()
                    .viewOrder()
                    .as(new ViewName[] { ViewName.DASHBOARD, ViewName.CATEGORY, ViewName.TEST })
                    .apply();//specify location of the report
            sparkReporter.config().setDocumentTitle("Automation Report"); // Title of report
            sparkReporter.config().setEncoding("utf-8");
            sparkReporter.config().setProtocol(Protocol.HTTPS);

            File logo = new File(REPORT_LOGO_FILE);
            sparkReporter.config().setReportName("<img src='test-output/logo.png.png'/>");
            sparkReporter.config().setCss(".header { background-color: #fff !important; }" +
                    ".badge { color: black !important; }" +
                    ".badge-primary { background-color: #fff !important; }" +
                    "img { width: 70%; }");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            extent.setSystemInfo("Operating System", System.getProperty("os.name"));
            extent.setSystemInfo("User Name", System.getProperty("user.name"));
            extent.setSystemInfo("Environemnt", "QA");
        }

        return extent;
    }
}
