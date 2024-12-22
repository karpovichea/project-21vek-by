package by.vek21.ui.step;

import by.vek21.ui.page.HomePage;
import org.openqa.selenium.WebDriver;

public class OpenProductPageStep {

    private final HomePage homePage;

    public OpenProductPageStep(WebDriver driver) {
        homePage = new HomePage(driver);
    }

    public void openProductPageByPopularCategory() {
        homePage
                .waitForLoad()
                .clickOnLastPopularCategory();
    }
}
