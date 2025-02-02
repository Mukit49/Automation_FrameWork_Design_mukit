package Pages;

import Components.Button;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.awt.*;

public class RegistrationPage extends BasePage{

   private static final By REGISTER_ACCOUNT_TEXT = By.xpath("//div[@id='content']/h1[text()='Register Account']");
   private static final By FIRST_NAME_INPUT = By.xpath("//label[text()='First Name']/following-sibling::div/input[@id='input-firstname']");
   private static final By LAST_NAME_INPUT = By.xpath("//label[text()='Last Name']/following-sibling::div/input[@id='input-lastname']");
   private static final By EMAIL_INPUT = By.xpath("//label[text()='E-Mail']/following-sibling::div/input[@id='input-email']");
   private static final By TELEPHONE_INPUT = By.xpath("//label[text()='Telephone']/following-sibling::div/input[@id='input-telephone']");
   private static final By PASSWORD_INPUT = By.xpath("//label[text()='Password']/following-sibling::div/input[@id='input-password']");
   private static final By CONFIRM_PASSWORD_INPUT = By.xpath("//label[text()='Password Confirm']/following-sibling::div/input[@id='input-confirm']");
   private static final By SUBSCRIBE_YES_RADIO = By.xpath("(//label[contains(text(), 'Subscribe')]/following-sibling::div/label/input)[1]");
   private static final By SUBSCRIBE_NO_RADIO = By.xpath("(//label[contains(text(), 'Subscribe')]/following-sibling::div/label/input)[2]");
   private static final By PRIVACY_POLICY_CHECKBOX = By.xpath("//a[@class='agree']/following-sibling::input[@type='checkbox']");
   private static final By CONTINUE_BUTTON = By.xpath("//a[@class='agree']/following-sibling::input[@value='Continue']");

   public RegistrationPage(WebDriver driver){
      super(driver);
   }

   public boolean isRegisterAccountTextDisplayed() {
      return getWebElements(REGISTER_ACCOUNT_TEXT).size() > 0;
   }

   public RegistrationPage inputFirstName(String firstname) {
      getWebElement(FIRST_NAME_INPUT).sendKeys(firstname);
      return this;
   }

   public RegistrationPage inputLastName(String lastname) {
      getWebElement(LAST_NAME_INPUT).sendKeys(lastname);
      return this;
   }

   public RegistrationPage inputEmail(String email) {
      getWebElement(EMAIL_INPUT).sendKeys(email);
      return this;
   }

   public RegistrationPage inputTelephoneNo(String telephoneNo) {
      getWebElement(TELEPHONE_INPUT).sendKeys(telephoneNo);
      return this;
   }

   public RegistrationPage inputPassword(String password) {
      getWebElement(PASSWORD_INPUT).sendKeys(password);
      return this;
   }

   public RegistrationPage inputConfirmPassword(String confirmPassword) {
      getWebElement(CONFIRM_PASSWORD_INPUT).sendKeys(confirmPassword);
      return this;
   }

   public RegistrationPage clickOnSubscribeYesRadioBtn() {
      getWebElement(SUBSCRIBE_YES_RADIO).click();
      return this;
   }

   public RegistrationPage clickOnSubscribeNoRadioBtn() {
      getWebElement(SUBSCRIBE_NO_RADIO).click();
      return this;
   }

   public RegistrationPage selectNewsletterSubscribe(String subscription) {
      if (subscription.equalsIgnoreCase("yes")) {
         clickOnSubscribeYesRadioBtn();
      } else {
         clickOnSubscribeNoRadioBtn();
      }
      return this;
   }

   public RegistrationPage clickOnPrivacyPolicyCheckBox() {
      getWebElement(PRIVACY_POLICY_CHECKBOX).click();
      return this;
   }

   public void clickOnContinueBtn() {
      getWebElement(CONTINUE_BUTTON).click();
   }

   public boolean isSuccessMessageDisplayed() throws InterruptedException {

      String successMessage = "//h1[contains(text(), 'Your Account Has Been Created!')]";

      return getWebElements(By.xpath(successMessage)).size() > 0;
   }

}
