package by.vek21.ui;

import by.vek21.domain.User;
import by.vek21.ui.page.login.LoginPageMessages;
import by.vek21.ui.page.login.LoginPage;
import by.vek21.ui.step.LoginStep;
import by.vek21.ui.step.OpenLoginPageStep;
import by.vek21.util.GenerateUsers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LoginByPhoneNumberUiTest extends BaseUiTest {

    private LoginPage loginPage;
    private LoginStep loginStep;

    @BeforeEach
    public void setUpLoginTest() {
        new OpenLoginPageStep().openLoginPage();
        loginStep = new LoginStep();
        loginPage = new LoginPage();
    }

    @Test
    @DisplayName("Авторизация с незарегистрированным номером телефона")
    public void testLoginWithUnregisteredPhoneNumber() {
        logger.info("ЗАПУСК ТЕСТА: Авторизация с незарегистрированным номером телефона");

        User user = GenerateUsers.getUserWithUnregisteredPhoneUi();

        loginStep.fillLoginFormByPhoneNumberAndSubmit(user);

        Assertions.assertEquals(LoginPageMessages.UNREGISTERED_PHONE_NUMBER_ERROR_MESSAGE, loginPage.getPhoneNumberErrorMessage(), "Некорректный текст ошибки");

        logger.info("ЗАВЕРШЕНИЕ ТЕСТА: Авторизация с незарегистрированным номером телефона");
    }

    @Test
    @DisplayName("Авторизация с невалидным кодом оператора")
    public void testLoginWithInvalidCodeNumber() {
        logger.info("ЗАПУСК ТЕСТА: Авторизация с невалидным кодом оператора");

        User user = GenerateUsers.getUserWithInvalidPhoneCodeUi();

        loginStep.fillLoginFormByPhoneNumberAndSubmit(user);

        Assertions.assertEquals(LoginPageMessages.INVALID_CODE_NUMBER_ERROR_MESSAGE, loginPage.getPhoneNumberErrorMessage(), "Некорректный текст ошибки");

        logger.info("ЗАВЕРШЕНИЕ ТЕСТА: Авторизация с невалидным кодом оператора");
    }
}
