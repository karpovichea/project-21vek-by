package by.vek21.ui.step;

import by.vek21.ui.page.home.HomePage;
import org.openqa.selenium.WebDriver;

public class OpenProductPageStep {

    private final HomePage homePage;

    public OpenProductPageStep() {
        homePage = new HomePage();
    }

    public void openProductPageByPopularCategory() {
        homePage
                .waitForLoad()
                .clickOnLastPopularCategory();
    }
}
