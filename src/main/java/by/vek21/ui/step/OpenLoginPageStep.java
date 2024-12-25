package by.vek21.ui.step;

import by.vek21.ui.page.home.HomePage;
import org.openqa.selenium.WebDriver;

public class OpenLoginPageStep {

    private final HomePage homePage;

    public OpenLoginPageStep() {
        homePage = new HomePage();
    }

    public void openLoginPage() {
        homePage
                .waitForLoad()
                .clickAccountButton()
                .clickLoginButton();
    }
}
