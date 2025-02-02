package Pages;

import Components.Button;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;

public class AddToCartPage extends BasePage {

    private static final By QUANTITY_INPUT = By.xpath("//label[text() = 'Qty']/following-sibling::input[@id = 'input-quantity']");
    private static final By ADD_TO_CART_BUTTON = By.xpath("(//button[normalize-space() = 'Add to Cart'])[1]");
    private static final By SHOPPING_CART_LINK = By.xpath("//a//span[text() = 'Shopping Cart']");

    public AddToCartPage(WebDriver driver) {
        super(driver);
    }

    public AddToCartPage InputProductQuantity(int quantity) {
        getWebElement(QUANTITY_INPUT).clear();
        getWebElement(QUANTITY_INPUT).sendKeys(String.valueOf(quantity));
        return this;
    }

    public AddToCartPage clickOnAddToCartBtn() {
        getWebElement(ADD_TO_CART_BUTTON).click();
        waitForElementToBeVisible(SHOPPING_CART_LINK);
        System.out.println("Add to cart is clicked");
        return this;
    }

    public void clickOnShoppingCartLink() {
        waitForElementToBeVisible(SHOPPING_CART_LINK);
        WebElement shoppingCartLink = getWebElement(SHOPPING_CART_LINK);
        shoppingCartLink.click();
    }
}

