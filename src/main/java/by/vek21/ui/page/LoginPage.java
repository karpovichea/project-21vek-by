package by.vek21.ui.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//span[@class='Text-module__text Text-module__caption']")
    private List<WebElement> radioButtons;

    @FindBy(xpath = "//input[@type='tel']")
    private WebElement phoneNumberField;

    @FindBy(xpath = "//span[contains(@class, 'ErrorMessage-module__message')]")
    private WebElement phoneNumberErrorMessage;

    @FindBy(xpath = "//input[@data-testid='login-form-email']")
    private WebElement emailField;

    @FindBy(xpath = "//span[contains(@class, 'ErrorMessage-module__message')]")
    private WebElement emailErrorMessage;

    @FindBy(xpath = "//input[@data-testid='login-form-password']")
    private WebElement passwordField;

    @FindBy(xpath = "(//span[contains(@class, 'ErrorMessage-module__message')])[last()]")
    private WebElement passwordErrorMessage;

    @FindBy(className = "BaseInput-module__icon")
    private WebElement showPasswordIcon;

    @FindBy(xpath = "//button[@data-testid='loginSubmit']")
    private WebElement continueByEmailButton;

    @FindBy(xpath = "//button[contains(@class, 'Button-module__button Button-module__blue-primary')]")
    private WebElement continueByPhoneNumberButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public LoginPage waitForLoad() {
        wait.until(ExpectedConditions.visibilityOf(continueByEmailButton));
        return this;
    }

    public LoginPage clickLoginByPhoneNumberRadioButton() {
        radioButtons.stream()
                .findFirst()
                .ifPresent(WebElement::click);
        return this;
    }

    public String getPhoneNumberErrorMessage() {
        wait.until(ExpectedConditions.visibilityOf(passwordErrorMessage));
        return phoneNumberErrorMessage.getText();
    }

    public LoginPage fillPhoneNumber(String phoneNumber) {
        phoneNumberField.sendKeys(phoneNumber);
        return this;
    }

    public String getEmailErrorMessage() {
        wait.until(ExpectedConditions.visibilityOf(passwordErrorMessage));
        return emailErrorMessage.getText();
    }

    public LoginPage fillEmail(String email) {
        emailField.sendKeys(email);
        return this;
    }

    public String getPasswordErrorMessage() {
        wait.until(ExpectedConditions.visibilityOf(passwordErrorMessage));
        return passwordErrorMessage.getText();
    }

    public String getPasswordFieldType() {
        return passwordField.getDomAttribute("type");
    }

    public String getPasswordFieldValue() {
        return passwordField.getDomAttribute("value");
    }

    public LoginPage fillPassword(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    public void clickShowPasswordIcon() {
        showPasswordIcon.click();
    }

    public void clickContinueByEmailButton() {
        continueByEmailButton.click();
    }

    public void clickContinueByPhoneNumberButton() {
        continueByPhoneNumberButton.click();
    }
}
