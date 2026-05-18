package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {

    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;

    // Store report name
    String repName;

    // Executes before all test methods
    public void onStart(ITestContext testContext) {

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName = "Test-Report-" + timeStamp + ".html";
        sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);

        sparkReporter.config().setDocumentTitle("opencart Automation Report");
        sparkReporter.config().setReportName("opencart Functional Testing");
        sparkReporter.config().setTheme(Theme.DARK);

        // Initialize ExtentReports
        extent = new ExtentReports();
        // Attach reporter to ExtentReports
        extent.attachReporter(sparkReporter);

        // Add system information to report
        extent.setSystemInfo("Application", "opencart");
        extent.setSystemInfo("Module", "Admin");
        extent.setSystemInfo("Sub Module", "Customers");
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");

        // Get OS parameter from testng.xml
        String os = testContext.getCurrentXmlTest().getParameter("os");
        extent.setSystemInfo("Operating System", os);

        // Get browser parameter from testng.xml
        String browser = testContext.getCurrentXmlTest().getParameter("browser");
        extent.setSystemInfo("Browser", browser);

        // Get included groups from testng.xml
        List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();

        // Add groups information if available
        if (!includedGroups.isEmpty()) {

            extent.setSystemInfo("Groups", includedGroups.toString());
        }
    }

    // Executes when test case passes
    public void onTestSuccess(ITestResult result) {

        // Create test entry in report
        test = extent.createTest(result.getTestClass().getName());

        // Assign category/groups
        test.assignCategory(result.getMethod().getGroups());

        // Log PASS status
        test.log(Status.PASS, result.getName() + " got successfully executed");
    }

    // Executes when test case fails
    public void onTestFailure(ITestResult result) {

        // Create test entry in report
        test = extent.createTest(result.getTestClass().getName());

        // Assign category/groups
        test.assignCategory(result.getMethod().getGroups());

        // Log FAIL status
        test.log(Status.FAIL, result.getName() + " got failed");

        // Log exception message
        test.log(Status.INFO, result.getThrowable().getMessage());

        try {

            // Capture screenshot
            String imgPath = new BaseClass().captureScreen(result.getName());

            // Attach screenshot to report
            test.addScreenCaptureFromPath(imgPath);

        } catch (IOException e1) {

            // Print exception
            e1.printStackTrace();
        }
    }

    // Executes when test case gets skipped
    public void onTestSkipped(ITestResult result) {

        // Create test entry in report
        test = extent.createTest(result.getTestClass().getName());

        // Assign category/groups
        test.assignCategory(result.getMethod().getGroups());

        // Log SKIP status
        test.log(Status.SKIP, result.getName() + " got skipped");

        // Log skip reason
        test.log(Status.INFO, result.getThrowable().getMessage());
    }

    // Executes after all test methods
    public void onFinish(ITestContext testContext) {

        extent.flush();

        String pathOfExtentReport =
                System.getProperty("user.dir") + "\\reports\\" + repName;

        // Create file object
        File extentReport = new File(pathOfExtentReport);

        try {
            Desktop.getDesktop().browse(extentReport.toURI()); 

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /*
      try {
      
          // Create URL object for extent report
          URL url = new URL("fil e:///" + System.getProperty("user.dir")+"\\reports\\"+repName);
      
          // Create the email message
         ImageHtmlEmail email = new ImageHtmlEmail();
      
         email.setDataSourceResolver(new DataSourceUrlResolver(url));     
         email.setHostName("smtp.googlemail.com");     
         email.setSmtpPort(465);     
         email.setAuthenticator(new DefaultAuthenticator("pavanoltraining@gmail.com", "password"));     
         email.setSSLOnConnect(true);     
         email.setFrom("pavanoltraining@gmail.com"); // Sender     
         email.setSubject("Test Results");      
         email.setMsg("Please find Attached Report.....");     
         email.addTo("pavankumar.busyqa@gmail.com"); // Receiver      
         email.attach(url, "extent report", "please check report...");
         email.send();
      
	     } catch (Exception e) {
	          e.printStackTrace();
	     }
	     */
     
}