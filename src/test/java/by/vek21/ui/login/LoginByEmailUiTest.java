package by.vek21.ui.login;

import by.vek21.ui.BaseUiTest;
import by.vek21.domain.User;
import by.vek21.ui.page.login.LoginPageMessages;
import by.vek21.ui.page.login.LoginPage;
import by.vek21.ui.step.LoginStep;
import by.vek21.ui.step.OpenLoginPageStep;
import by.vek21.ui.util.GenerateDataUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LoginByEmailUiTest extends BaseUiTest {

    private static final String EMPTY_VALUE = "";

    private LoginPage loginPage;
    private LoginStep loginStep;

    @BeforeEach
    public void setUpLoginTest() {
        new OpenLoginPageStep().openLoginPage();
        loginStep = new LoginStep();
        loginPage = new LoginPage();
    }

    @Test
    @DisplayName("Авторизация c незарегистрированной электронной почтой")
    public void testLoginWithUnregisteredEmail() {
        logger.info("ЗАПУСК ТЕСТА: Авторизация c незарегистрированной электронной почтой");

        String unregisteredEmail = "unregistred@gmail.com";
        String password = GenerateDataUtil.generatePassword();
        User user = new User(unregisteredEmail, password);

        loginStep.fillLoginFormByEmailAndSubmit(user);

        Assertions.assertEquals(LoginPageMessages.UNREGISTERED_EMAIL_ERROR_MESSAGE, loginPage.getEmailErrorMessage(), "Некорректный текст ошибки");

        logger.info("ЗАВЕРШЕНИЕ ТЕСТА: Авторизация c незарегистрированной электронной почтой");
    }

    @Test
    @DisplayName("Авторизация с неверным паролем")
    public void testLoginWithInvalidPassword() {
        logger.info("ЗАПУСК ТЕСТА: Авторизация с неверным паролем");

        String registeredEmail = "email@gmail.com";
        String invalidPassword = GenerateDataUtil.generatePassword();
        User user = new User(registeredEmail, invalidPassword);

        loginStep.fillLoginFormByEmailAndSubmit(user);

        Assertions.assertEquals(LoginPageMessages.INVALID_PASSWORD_ERROR_MESSAGE, loginPage.getPasswordErrorMessage(), "Некорректный текст ошибки");

        logger.info("ЗАВЕРШЕНИЕ ТЕСТА: Авторизация с неверным паролем");
    }

    @Test
    @DisplayName("Авторизация с незаполненной электронной почтой")
    public void testLoginWithEmptyEmail() {
        logger.info("ЗАПУСК ТЕСТА: Авторизация с незаполненной электронной почтой");

        String password = GenerateDataUtil.generatePassword();
        User user = new User(EMPTY_VALUE, password);

        loginStep.fillLoginFormByEmailAndSubmit(user);

        Assertions.assertEquals(LoginPageMessages.EMPTY_EMAIL_ERROR_MESSAGE, loginPage.getEmailErrorMessage(), "Некорректный текст ошибки");

        logger.info("ЗАВЕРШЕНИЕ ТЕСТА: Авторизация с незаполненной электронной почтой");
    }

    @Test
    @DisplayName("Авторизация с незаполненным паролем")
    public void testLoginWithEmptyPassword() {
        logger.info("ЗАПУСК ТЕСТА: Авторизация с незаполненным паролем");

        String email = GenerateDataUtil.generateEmail();
        User user = new User(email, EMPTY_VALUE);

        loginStep.fillLoginFormByEmailAndSubmit(user);

        Assertions.assertEquals(LoginPageMessages.EMPTY_PASSWORD_ERROR_MESSAGE, loginPage.getPasswordErrorMessage(), "Некорректный текст ошибки");

        logger.info("ЗАВЕРШЕНИЕ ТЕСТА: Авторизация с незаполненным паролем");
    }

    @Test
    @DisplayName("Авторизация с незаполненными полями")
    public void testLoginWithEmptyData() {
        logger.info("ЗАПУСК ТЕСТА: Авторизация с незаполненными полями");

        User user = new User(EMPTY_VALUE, EMPTY_VALUE);

        loginStep.fillLoginFormByEmailAndSubmit(user);

        Assertions.assertAll(
                () -> Assertions.assertEquals(LoginPageMessages.EMPTY_EMAIL_ERROR_MESSAGE, loginPage.getEmailErrorMessage(), "Некорректный текст ошибки"),
                () -> Assertions.assertEquals(LoginPageMessages.EMPTY_PASSWORD_ERROR_MESSAGE, loginPage.getPasswordErrorMessage(), "Некорректный текст ошибки")
        );

        logger.info("ЗАВЕРШЕНИЕ ТЕСТА: Авторизация с незаполненными полями");
    }

    @Test
    @DisplayName("Сокрытие введенного пароля")
    public void testMaskPassword() {
        logger.info("ЗАПУСК ТЕСТА: Сокрытие введенного пароля");

        String password = GenerateDataUtil.generatePassword();
        String fieldType = "password";

        loginPage.fillPassword(password);

        Assertions.assertAll(
                () -> Assertions.assertEquals(fieldType, loginPage.getPasswordFieldType(), "Неверный тип поля"),
                () -> Assertions.assertEquals(password, loginPage.getPasswordFieldValue(), "Значение в поле отличается")
        );

        logger.info("ЗАВЕРШЕНИЕ ТЕСТА: Сокрытие введенного пароля");
    }

    @Test
    @DisplayName("Отображение введенного пароля")
    public void testShowPassword() {
        logger.info("ЗАПУСК ТЕСТА: Отображение введенного пароля");

        String password = GenerateDataUtil.generatePassword();
        String fieldType = "text";

        loginPage
                .fillPassword(password)
                .clickShowPasswordIcon();

        Assertions.assertAll(
                () -> Assertions.assertEquals(fieldType, loginPage.getPasswordFieldType(), "Неверный тип поля"),
                () -> Assertions.assertEquals(password, loginPage.getPasswordFieldValue(), "Значение в поле отличается")
        );

        logger.info("ЗАВЕРШЕНИЕ ТЕСТА: Отображение введенного пароля");
    }
}
