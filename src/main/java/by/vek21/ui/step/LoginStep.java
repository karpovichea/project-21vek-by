package by.vek21.ui.step;

import by.vek21.domain.User;
import by.vek21.ui.page.LoginPage;
import org.openqa.selenium.WebDriver;

public class LoginStep {

    private final LoginPage loginPage;

    public LoginStep(WebDriver driver) {
        loginPage = new LoginPage(driver);
    }

    public void fillLoginFormByEmailAndSubmit(User user) {
        loginPage
                .waitForLoad()
                .fillEmail(user.getEmail())
                .fillPassword(user.getPassword())
                .clickContinueByEmailButton();
    }

    public void fillLoginFormByPhoneNumberAndSubmit(User user) {
        loginPage
                .waitForLoad()
                .clickLoginByPhoneNumberRadioButton()
                .fillPhoneNumber(user.getPhone())
                .clickContinueByPhoneNumberButton();
    }
}
