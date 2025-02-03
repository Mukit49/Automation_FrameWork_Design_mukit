package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public WebElement getWebElement(By locator) {
        WebElement webElement = null;
        try {
            waitForWebElement(locator);
            webElement = driver.findElement(locator);
        } catch (Exception e) {
            System.out.println(locator.toString() + " Not Found");
        }
        return webElement;
    }

    public List<WebElement> getWebElements(By locator) {
        List<WebElement> webElements = null;
        try {
            waitForWebElement(locator);
            webElements = driver.findElements(locator);
        } catch (Exception e) {
            System.out.println(locator.toString() + " Not Found");
        }
        return webElements;
    }

    public void waitForWebElement(By locator) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception e) {
            System.out.println(locator.toString() + " Not found");
        }
    }

    public Select selectValueFromDropdown(WebElement element) {
        return new Select(element);
    }

    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public <T extends BasePage> T getInstance(Class<T> tClass) {
        try {
            return tClass.getDeclaredConstructor(WebDriver.class).newInstance(this.driver);
        } catch (Exception e) {
            throw new RuntimeException("Error creating instance of: " + tClass.getSimpleName(), e);
        }
    }

    public void waitForElementToBeVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }


//    public void setInfo(String message) {
//        if (ReportTestManager.getTest() != null) {
//            ReportTestManager.getTest().log(Status.INFO, message);
//        }
//    }
}

