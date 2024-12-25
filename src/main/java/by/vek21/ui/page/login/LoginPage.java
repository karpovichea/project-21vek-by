package by.vek21.ui.page.login;

import by.vek21.ui.page.BasePage;
import by.vek21.ui.wait.Wait;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    public LoginPage() {
        super();
    }

    @Override
    public LoginPage waitForLoad() {
        Wait.waitForVisibility(continueByEmailButton);
        return this;
    }

    public LoginPage clickLoginByPhoneNumberRadioButton() {
        radioButtons.stream()
                .findFirst()
                .ifPresent(WebElement::click);
        return this;
    }

    public String getPhoneNumberErrorMessage() {
        Wait.waitForVisibility(phoneNumberErrorMessage);
        return phoneNumberErrorMessage.getText();
    }

    public LoginPage fillPhoneNumber(String phoneNumber) {
        phoneNumberField.sendKeys(phoneNumber);
        return this;
    }

    public String getEmailErrorMessage() {
        Wait.waitForVisibility(emailErrorMessage);
        return emailErrorMessage.getText();
    }

    public LoginPage fillEmail(String email) {
        emailField.sendKeys(email);
        return this;
    }

    public String getPasswordErrorMessage() {
        Wait.waitForVisibility(passwordErrorMessage);
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
