package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver){
        super(driver);
    }

    private static final By MY_ACCOUNT_MENU = By.xpath("//span[contains(text(), 'My Account')]");
    private static final By REGISTER_BTN = By.xpath("//li//a[contains(text(), 'Register')]");
    private static final By DESKTOPS_MENU = By.xpath("//a[contains(@class, 'dropdown-toggle') and text()='Desktops']");
    private static final By MAC_CATEGORY = By.xpath("//a[text()='Mac (1)']");

    // 🔹 Methods using locators
    public HomePage clickOnMyAccountMenuBtn() {

        if (driver == null) {
            throw new IllegalStateException("Driver is NULL in HomePage. Ensure it's initialized properly.");
        }
        WebElement myAccountElement = wait.until(ExpectedConditions.elementToBeClickable(MY_ACCOUNT_MENU));
        myAccountElement.click();
        return this;
    }

    public RegistrationPage clickOnRegisterBtn() {
        getWebElement(REGISTER_BTN).click();
        return getInstance(RegistrationPage.class);
    }

    public void hoverOnDesktopMenu() {
        Actions actions = new Actions(driver);
        actions.moveToElement(getWebElement(DESKTOPS_MENU)).build().perform();
        getWebElement(MAC_CATEGORY).click();

    }


}
