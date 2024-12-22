package by.vek21.ui.page;

import by.vek21.ui.util.UserActionUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class HomePage extends BasePage {

    @FindBy(xpath = "//div[@data-testid='header-count']")
    private WebElement cartButton;

    @FindBy(className = "userToolsText")
    private WebElement accountButton;

    @FindBy(xpath = "//button[@data-testid='loginButton']")
    private WebElement loginButton;

    @FindBy(className = "styles_promoItem__aolWq")
    private List<WebElement> promotedCategories;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public HomePage waitForLoad() {
        wait.until(ExpectedConditions.elementToBeClickable(accountButton));
        return this;
    }

    public void clickCartButton() {
        UserActionUtil.simulateUserDelay();
        cartButton.click();
    }

    public HomePage clickAccountButton() {
        accountButton.click();
        return this;
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public void clickOnLastPopularCategory() {
        promotedCategories.getLast().click();
    }
}
