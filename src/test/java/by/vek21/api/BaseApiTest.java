package by.vek21.api;

import by.vek21.ui.util.ActionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;

public class BaseApiTest {
    protected static final Logger logger = LogManager.getLogger();

    @AfterEach
    public void tearDown() {
        ActionUtil.simulateUserDelay();
    }
}
