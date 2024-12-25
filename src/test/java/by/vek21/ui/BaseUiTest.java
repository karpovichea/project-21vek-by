package by.vek21.ui;

import by.vek21.ui.driver.Driver;
import by.vek21.ui.util.UserActionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BaseUiTest {

    private static final String BASE_URL = "https://www.21vek.by/";
    protected static final Logger logger = LogManager.getLogger();

    @BeforeEach
    public void setUp() {
        Driver.getDriver().get(BASE_URL);

        Driver.setLocalStorageItem();

//        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
//        js.executeScript(
//                "localStorage.setItem('partner-cookies', JSON.stringify({\"value\":{\"isAnalyticsCookies\":true,\"isFunctionalCookies\":true},\"expireIn\":null}));"
//        );
        Driver.getDriver().navigate().refresh();
    }

    @AfterEach
    public void tearDown() {
        UserActionUtil.simulateUserDelay();
        Driver.quit();
    }
}
