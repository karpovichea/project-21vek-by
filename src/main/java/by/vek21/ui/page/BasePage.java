package by.vek21.ui.page;

import by.vek21.ui.driver.Driver;
import by.vek21.ui.wait.Wait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;


public abstract class BasePage {

    protected WebDriverWait wait;
    protected WebDriver driver;

    public BasePage() {
        driver = Driver.getDriver();
        wait = Wait.getWait();
        PageFactory.initElements(driver, this);
    }

    public abstract BasePage waitForLoad();
}
