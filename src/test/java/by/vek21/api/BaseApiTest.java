package by.vek21.api;

import by.vek21.ui.util.UserActionUtil;
import org.junit.jupiter.api.AfterEach;

public class BaseApiTest {

    @AfterEach
    public void tearDown() {
        UserActionUtil.simulateUserDelay();
    }
}
