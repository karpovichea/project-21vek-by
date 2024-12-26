package by.vek21.ui;

import by.vek21.ui.driver.Driver;
import by.vek21.ui.util.ActionUtil;
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
        Driver.getDriver().navigate().refresh();
    }

    @AfterEach
    public void tearDown() {
        ActionUtil.simulateUserDelay();
        Driver.quit();
    }
}
