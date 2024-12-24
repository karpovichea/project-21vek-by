package by.vek21.ui;

import by.vek21.ui.driver.ConfigDriver;
import by.vek21.ui.util.UserActionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class BaseUiTest {

    private static final String BASE_URL = "https://www.21vek.by/";
    protected static final Logger logger = LogManager.getLogger();
    protected WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = ConfigDriver.getDriver();
        driver.get(BASE_URL);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
                "localStorage.setItem('partner-cookies', JSON.stringify({\"value\":{\"isAnalyticsCookies\":true,\"isFunctionalCookies\":true},\"expireIn\":null}));"
        );
        driver.navigate().refresh();
    }

    @AfterEach
    public void tearDown() {
        UserActionUtil.simulateUserDelay();
        ConfigDriver.quit();
    }
}
