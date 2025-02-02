package TestCases;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.safari.SafariDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

    public WebDriver driver;
    private Properties properties;

    public BaseTest() {
        properties = new Properties();
        try {
            // Load the properties file
            FileInputStream input = new FileInputStream("src/main/resources/config.properties");
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initializeDriver() {
        String browserName = properties.getProperty("browser");

        if (browserName == null) {
            throw new IllegalArgumentException("Browser name is not specified in the properties file.");
        }

        switch (browserName.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup(); // Automatically downloads and sets up ChromeDriver
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup(); // Automatically downloads and sets up GeckoDriver
                driver = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup(); // Automatically downloads and sets up EdgeDriver
                driver = new EdgeDriver();
                break;
            case "safari":
                // SafariDriver does not require a separate driver executable
                driver = new SafariDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }

        driver.manage().window().maximize();
    }

    public String getWebsiteUrl() {
        String websiteUrl = properties.getProperty("website_url");
        if (websiteUrl == null) {
            throw new IllegalArgumentException("Website URL is not specified in the properties file.");
        }
        return websiteUrl;
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}