package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

    public static WebDriver driver;
    public Logger logger;
    public Properties p;

    @BeforeClass
    @Parameters({"browser"})
    public void setup(String br) throws Exception {

        p = new Properties();
        p.load(new FileInputStream("./src/test/resources/config.properties"));

        logger = LogManager.getLogger(this.getClass());

        URL gridURL = new URL("http://localhost:4444/wd/hub");

        ChromeOptions options = new ChromeOptions();

        driver = new RemoteWebDriver(gridURL, options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        driver.get("https://tutorialsninja.com/demo/");
    }

    @AfterClass
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }

    // screenshot
    public String captureScreen(String tname) throws IOException {

        String targetFilePath =
                System.getProperty("user.dir") + "\\screenshots\\" + tname + ".png";

        TakesScreenshot ts = (TakesScreenshot) driver;

        File sourceFile = ts.getScreenshotAs(OutputType.FILE);
        File targetFile = new File(targetFilePath);

        FileUtils.copyFile(sourceFile, targetFile);

        return targetFilePath;
    }

    // random string
    public String randomString() {
        return RandomStringUtils.randomAlphabetic(5);
    }

    // random number
    public String randomNumber() {
        return RandomStringUtils.randomNumeric(10);
    }

    // random alpha numeric
    public String randomAlphaNumeric() {
        return RandomStringUtils.randomAlphanumeric(10);
    }
}