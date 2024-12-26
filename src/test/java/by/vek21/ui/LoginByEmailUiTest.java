package by.vek21.ui;

import by.vek21.domain.User;
import by.vek21.ui.page.login.LoginPageMessages;
import by.vek21.ui.page.login.LoginPage;
import by.vek21.ui.step.LoginStep;
import by.vek21.ui.step.OpenLoginPageStep;
import by.vek21.util.GenerateDataUtil;
import by.vek21.util.GenerateUsers;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("UI тесты")
@Feature("Авторизация с помощью электронной почты и пароля")
public class LoginByEmailUiTest extends BaseUiTest {

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

        User user = GenerateUsers.getUserWithUnregisteredEmailAndAnyPassword();

        loginStep.fillLoginFormByEmailAndSubmit(user);

        Assertions.assertEquals(LoginPageMessages.UNREGISTERED_EMAIL_ERROR_MESSAGE, loginPage.getEmailErrorMessage(), "Некорректный текст ошибки");

        logger.info("ЗАВЕРШЕНИЕ ТЕСТА: Авторизация c незарегистрированной электронной почтой");
    }

    @Test
    @DisplayName("Авторизация с неверным паролем")
    public void testLoginWithInvalidPassword() {
        logger.info("ЗАПУСК ТЕСТА: Авторизация с неверным паролем");

        User user = GenerateUsers.getUserWithRegisteredEmailAndInvalidPassword();

        loginStep.fillLoginFormByEmailAndSubmit(user);

        Assertions.assertEquals(LoginPageMessages.INVALID_PASSWORD_ERROR_MESSAGE, loginPage.getPasswordErrorMessage(), "Некорректный текст ошибки");

        logger.info("ЗАВЕРШЕНИЕ ТЕСТА: Авторизация с неверным паролем");
    }

    @Test
    @DisplayName("Авторизация с незаполненной электронной почтой")
    public void testLoginWithEmptyEmail() {
        logger.info("ЗАПУСК ТЕСТА: Авторизация с незаполненной электронной почтой");

        User user = GenerateUsers.getUserWithEmptyEmailAndAnyPassword();

        loginStep.fillLoginFormByEmailAndSubmit(user);

        Assertions.assertEquals(LoginPageMessages.EMPTY_EMAIL_ERROR_MESSAGE, loginPage.getEmailErrorMessage(), "Некорректный текст ошибки");

        logger.info("ЗАВЕРШЕНИЕ ТЕСТА: Авторизация с незаполненной электронной почтой");
    }

    @Test
    @DisplayName("Авторизация с незаполненным паролем")
    public void testLoginWithEmptyPassword() {
        logger.info("ЗАПУСК ТЕСТА: Авторизация с незаполненным паролем");

        User user = GenerateUsers.getUserWithAnyEmailAndEmptyPassword();

        loginStep.fillLoginFormByEmailAndSubmit(user);

        Assertions.assertEquals(LoginPageMessages.EMPTY_PASSWORD_ERROR_MESSAGE, loginPage.getPasswordErrorMessage(), "Некорректный текст ошибки");

        logger.info("ЗАВЕРШЕНИЕ ТЕСТА: Авторизация с незаполненным паролем");
    }

    @Test
    @DisplayName("Авторизация с незаполненными полями")
    public void testLoginWithEmptyData() {
        logger.info("ЗАПУСК ТЕСТА: Авторизация с незаполненными полями");

        User user = GenerateUsers.getUserWithEmptyEmailAndPassword();

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

        String anyPassword = GenerateDataUtil.generatePassword();
        String fieldType = "password";

        loginPage.fillPassword(anyPassword);

        Assertions.assertAll(
                () -> Assertions.assertEquals(fieldType, loginPage.getPasswordFieldType(), "Неверный тип поля"),
                () -> Assertions.assertEquals(anyPassword, loginPage.getPasswordFieldValue(), "Значение в поле отличается")
        );

        logger.info("ЗАВЕРШЕНИЕ ТЕСТА: Сокрытие введенного пароля");
    }

    @Test
    @DisplayName("Отображение введенного пароля")
    public void testShowPassword() {
        logger.info("ЗАПУСК ТЕСТА: Отображение введенного пароля");

        String anyPassword = GenerateDataUtil.generatePassword();
        String fieldType = "text";

        loginPage
                .fillPassword(anyPassword)
                .clickShowPasswordIcon();

        Assertions.assertAll(
                () -> Assertions.assertEquals(fieldType, loginPage.getPasswordFieldType(), "Неверный тип поля"),
                () -> Assertions.assertEquals(anyPassword, loginPage.getPasswordFieldValue(), "Значение в поле отличается")
        );

        logger.info("ЗАВЕРШЕНИЕ ТЕСТА: Отображение введенного пароля");
    }
}
