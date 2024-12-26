package by.vek21.ui.step;

import by.vek21.ui.page.home.HomePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class OpenProductPageStep {

    private final HomePage homePage;

    public OpenProductPageStep() {
        homePage = new HomePage();
    }

    @Step("Перейти на страницу товаров")
    public void openProductPageByPopularCategory() {
        homePage
                .waitForLoad()
                .clickOnLastPopularCategory();
    }
}
