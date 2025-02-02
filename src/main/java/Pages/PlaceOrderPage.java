package Pages;

import Components.Button;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class PlaceOrderPage extends BasePage {

    private static final By PLACE_ORDER_BUTTON = By.xpath("//a[text() = 'Checkout']");
    private static final By EXPAND_COUPON_CODE = By.xpath("(//a[text() ='Use Coupon Code ']//following::i[@class = 'fa fa-caret-down'])[1]");
    private static final By COUPON_INPUT = By.xpath("//label[text() = 'Enter your coupon here']/following-sibling::div/input[@id = 'input-coupon']");

    private static final By ESTIMATE_SHIPPING_TAXES = By.xpath("//a[contains(text(), 'Estimate Shipping')]");
    private static final By CONTINUE_SHOPPING_BUTTON = By.xpath("//a[normalize-space() = 'Continue Shopping']");
    private static final By COUNTRY_DROPDOWN = By.xpath("//label[text() = 'Country']//following-sibling::div/select[@id = 'input-country']");
    private static final By REGION_DROPDOWN = By.xpath("//label[text() = 'Region / State']//following-sibling::div/select[@id = 'input-zone']");
    private static final By POSTCODE_INPUT = By.xpath("//label[text() = 'Post Code']//following-sibling::div/input[@id = 'input-postcode']");

    private static final By GIFT_CERTIFICATE_EXPAND = By.xpath("//a[normalize-space() = 'Use Gift Certificate']");
    private static final By GIFT_CERTIFICATE_INPUT = By.xpath("//label[contains(text(), 'Enter your gift certificate')]/following-sibling::div/input[@id = 'input-voucher']");

    private static final By ERROR_MESSAGE = By.xpath("//ul[@class = 'breadcrumb']/following-sibling::div[contains(@class, 'alert-danger')]");

    public PlaceOrderPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnIMacProduct() {
        getWebElement(By.xpath("//h4//a[contains(text(), 'iMac')]")).click();

    }

    public PlaceOrderPage clickOnPlaceOrderBtn() {
        getWebElement(PLACE_ORDER_BUTTON).click();
        return this;
    }

    public PlaceOrderPage inputUseCouponCode(String couponCode) {
        WebElement expandCouponCode = getWebElement(EXPAND_COUPON_CODE);
        expandCouponCode.click();
        getWebElement(COUPON_INPUT).sendKeys(couponCode);
        return this;
    }

    public PlaceOrderPage shipmentAndTaxes(int countryIndex, int regionIndex, int postalCode) {
        WebElement expandEstimateShippingAndTaxes = getWebElement(ESTIMATE_SHIPPING_TAXES);
        WebElement scrollToContinueBtn = getWebElement(CONTINUE_SHOPPING_BUTTON);

        scrollToElement(scrollToContinueBtn);
        waitForElementToBeVisible(ESTIMATE_SHIPPING_TAXES);

        expandEstimateShippingAndTaxes.click();
        System.out.println("Clicked on taxes expand");

        Select selectCountry = selectValueFromDropdown(getWebElement(COUNTRY_DROPDOWN));
        selectCountry.selectByIndex(countryIndex);

        Select region = selectValueFromDropdown(getWebElement(REGION_DROPDOWN));
        region.selectByIndex(regionIndex);

        getWebElement(POSTCODE_INPUT).sendKeys(String.valueOf(postalCode));
        return this;
    }

    public PlaceOrderPage giftCertificate(String giftCode) {
        WebElement expandUseGiftCertificate = getWebElement(GIFT_CERTIFICATE_EXPAND);
        expandUseGiftCertificate.click();
        getWebElement(GIFT_CERTIFICATE_INPUT).sendKeys(giftCode);
        return this;
    }

    public boolean isErrorMessageIsDisplayed() {
        return getWebElements(ERROR_MESSAGE).size() > 0;
    }

    public void hoverOnDesktopMenu() {
        Actions actions = new Actions(driver);
        actions.moveToElement(getWebElement(By.xpath("//a[contains(@class, 'dropdown-toggle') and text() = 'Desktops']"))).build().perform();

        getWebElement(By.xpath("//a[text() = 'Mac (1)']")).click();

    }
}
