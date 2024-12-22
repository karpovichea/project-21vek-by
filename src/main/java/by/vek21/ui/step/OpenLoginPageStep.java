package by.vek21.ui.step;

import by.vek21.ui.page.HomePage;
import org.openqa.selenium.WebDriver;

public class OpenLoginPageStep {

    private final HomePage homePage;

    public OpenLoginPageStep(WebDriver driver) {
        homePage = new HomePage(driver);
    }

    public void openLoginPage() {
        homePage
                .waitForLoad()
                .clickAccountButton()
                .clickLoginButton();
    }
}
