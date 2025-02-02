package TestCases;

import Pages.HomePage;
import Pages.RegistrationPage;
import Steps.RegistrationSteps;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RegistrationTest extends BaseTest {

    RegistrationSteps registrationSteps = new RegistrationSteps();

    private WebDriver driver;
    //private RegistrationSteps registrationSteps;
    private RegistrationPage registrationPage;
    private HomePage homePage;

    private String generateUniqueEmail() {
        return "user" + System.currentTimeMillis() + "@test.com";
    }



    @BeforeClass
    public void setUp() {
        initializeDriver();

        driver = getDriver();
        driver.get(getWebsiteUrl());

        registrationSteps = new RegistrationSteps();
        registrationPage = new RegistrationPage(driver);
        homePage = new HomePage(driver);
    }


    @Test
    public void testWebPageOpen(){
        String websiteUrl = getWebsiteUrl();
        driver.get(websiteUrl);
        System.out.println("Page title is: " + driver.getTitle());

    }

    @Test
    public void isRegistrationPageOpen(){

        homePage.clickOnMyAccountMenuBtn();
        homePage.clickOnRegisterBtn();
        Assert.assertTrue(registrationPage.isRegisterAccountTextDisplayed());
    }

    @DataProvider(name = "registrationData")
    public Object[][] getRegistrationData() throws IOException {
        String csvFile = "src/test/resources/registration_data.csv";
        BufferedReader reader = new BufferedReader(new FileReader(csvFile));
        String line;
        List<String[]> data = new ArrayList<>();

        while ((line = reader.readLine()) != null) {
            String[] row = line.split(",");
            data.add(row);
        }
        reader.close();

        return data.toArray(new Object[0][0]);
    }

    @Test(dataProvider = "registrationData")
    public void testUserRegistration(String firstName, String lastName, String email, String telephone, String password, String confirmPassword, String subscribe) throws InterruptedException {
        homePage.clickOnMyAccountMenuBtn();
        homePage.clickOnRegisterBtn();

        String uniqueEmail = generateUniqueEmail();

        registrationPage.inputFirstName(firstName)
                .inputLastName(lastName)
                .inputEmail(uniqueEmail)
                .inputTelephoneNo(telephone)
                .inputPassword(password)
                .inputConfirmPassword(confirmPassword)
                .selectNewsletterSubscribe(subscribe)
                .clickOnPrivacyPolicyCheckBox()
                .clickOnContinueBtn();

       Assert.assertTrue(registrationPage.isSuccessMessageDisplayed());

    }

//    public void testRegistrationSuccess(){
//        String websiteUrl = getWebsiteUrl();
//        driver.get(websiteUrl);
//    }




}
