package by.vek21.ui.step;

import by.vek21.ui.page.home.HomePage;
import io.qameta.allure.Step;

public class OpenLoginPageStep {

    private final HomePage homePage;

    public OpenLoginPageStep() {
        homePage = new HomePage();
    }

    @Step("Перейти на страницу логина")
    public void openLoginPage() {
        homePage
                .waitForLoad()
                .clickAccountButton()
                .clickLoginButton();
    }
}
