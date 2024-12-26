package by.vek21.ui.step;

import by.vek21.domain.User;
import by.vek21.ui.page.login.LoginPage;
import io.qameta.allure.Step;

public class LoginStep {

    private final LoginPage loginPage;

    public LoginStep() {
        loginPage = new LoginPage();
    }

    @Step("Заполнить емейл и пароль и нажать на кнопку 'Подтвердить'")
    public void fillLoginFormByEmailAndSubmit(User user) {
        loginPage
                .waitForLoad()
                .fillEmail(user.getEmail())
                .fillPassword(user.getPassword())
                .clickContinueByEmailButton();
    }

    @Step("Заполнить номер телефона и нажать на кнопку 'Подтвердить'")
    public void fillLoginFormByPhoneNumberAndSubmit(User user) {
        loginPage
                .waitForLoad()
                .clickLoginByPhoneNumberRadioButton()
                .fillPhoneNumber(user.getPhone())
                .clickContinueByPhoneNumberButton();
    }
}
