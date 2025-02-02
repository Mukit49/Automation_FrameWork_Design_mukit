package TestCases;

import Pages.AddToCartPage;
import Pages.HomePage;
import Pages.PlaceOrderPage;
import Pages.RegistrationPage;
import Steps.RegistrationSteps;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PlaceOrderTest extends BaseTest {

    private WebDriver driver;
    //private RegistrationSteps registrationSteps;
    private RegistrationPage registrationPage;


    private PlaceOrderPage placeOrderPage;

    private AddToCartPage addToCartPage;
    private HomePage homePage;
    @BeforeClass
    public void setUp() {
        initializeDriver();

        driver = getDriver();
        driver.get(getWebsiteUrl());

        registrationPage = new RegistrationPage(driver);
        homePage = new HomePage(driver);
        placeOrderPage = new PlaceOrderPage(driver);

        addToCartPage = new AddToCartPage(driver);
    }

    @Test
    public void testWebPageOpen(){
        String websiteUrl = getWebsiteUrl();
        driver.get(websiteUrl);
        System.out.println("Page title is: " + driver.getTitle());

    }

    @Test
    public void successfulOrderPlacementTest(){
        placeOrderPage.hoverOnDesktopMenu();
        placeOrderPage.clickOnIMacProduct();
        addToCartPage.InputProductQuantity(5);
        addToCartPage.clickOnAddToCartBtn();

        addToCartPage.clickOnShoppingCartLink();
        placeOrderPage.inputUseCouponCode("code");
        placeOrderPage.shipmentAndTaxes(5,2,60000);
        placeOrderPage.giftCertificate("certificate");
        placeOrderPage.clickOnPlaceOrderBtn();

        Assert.assertTrue(placeOrderPage.isErrorMessageIsDisplayed());
    }



}
