package by.vek21.ui.login;

import by.vek21.ui.BaseUiTest;
import by.vek21.ui.model.User;
import by.vek21.ui.page.LoginPage;
import by.vek21.ui.step.LoginStep;
import by.vek21.ui.step.OpenLoginPageStep;
import by.vek21.ui.util.GenerateDataUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LoginByEmailUiTest extends BaseUiTest {

    private static final String EMPTY_VALUE = "";
    private static final String EMPTY_EMAIL_ERROR_MESSAGE = "Электронная почта не указана";
    private static final String EMPTY_PASSWORD_ERROR_MESSAGE = "Пароль не указан";
    private static final String UNREGISTERED_EMAIL_ERROR_MESSAGE = "Проверьте электронную почту или \nзарегистрируйтесь";
    private static final String INVALID_PASSWORD_ERROR_MESSAGE = "Неправильный пароль. \nСбросить пароль?";

    private LoginPage loginPage;
    private LoginStep loginStep;

    @BeforeEach
    public void setUpLoginTest() {
        new OpenLoginPageStep(driver).openLoginPage();
        loginStep = new LoginStep(driver);
        loginPage = new LoginPage(driver);
    }

    @Test
    @DisplayName("Авторизация c незарегистрированной электронной почтой")
    public void testLoginWithUnregisteredEmail() {
        final String unregisteredEmail = "unregistred@gmail.com";
        final String password = GenerateDataUtil.generatePassword();

        User user = new User(unregisteredEmail, password);
        loginStep.fillLoginFormByEmailAndSubmit(user);

        Assertions.assertEquals(UNREGISTERED_EMAIL_ERROR_MESSAGE, loginPage.getEmailErrorMessage(), "Некорректный текст ошибки");
    }

    @Test
    @DisplayName("Авторизация с неверным паролем")
    public void testLoginWithInvalidPassword() {
        final String registeredEmail = "email@gmail.com";
        final String invalidPassword = GenerateDataUtil.generatePassword();

        User user = new User(registeredEmail, invalidPassword);
        loginStep.fillLoginFormByEmailAndSubmit(user);

        Assertions.assertEquals(INVALID_PASSWORD_ERROR_MESSAGE, loginPage.getPasswordErrorMessage(), "Некорректный текст ошибки");
    }

    @Test
    @DisplayName("Авторизация с незаполненной электронной почтой")
    public void testLoginWithEmptyEmail() {
        final String password = GenerateDataUtil.generatePassword();

        User user = new User(EMPTY_VALUE, password);
        loginStep.fillLoginFormByEmailAndSubmit(user);

        Assertions.assertEquals(EMPTY_EMAIL_ERROR_MESSAGE, loginPage.getEmailErrorMessage(), "Некорректный текст ошибки");
    }

    @Test
    @DisplayName("Авторизация с незаполненным паролем")
    public void testLoginWithEmptyPassword() {
        final String email = GenerateDataUtil.generateEmail();

        User user = new User(email, EMPTY_VALUE);
        loginStep.fillLoginFormByEmailAndSubmit(user);

        Assertions.assertEquals(EMPTY_PASSWORD_ERROR_MESSAGE, loginPage.getPasswordErrorMessage(), "Некорректный текст ошибки");
    }

    @Test
    @DisplayName("Авторизация с незаполненными полями")
    public void testLoginWithEmptyData() {
        User user = new User(EMPTY_VALUE, EMPTY_VALUE);
        loginStep.fillLoginFormByEmailAndSubmit(user);

        Assertions.assertAll(
                () -> Assertions.assertEquals(EMPTY_EMAIL_ERROR_MESSAGE, loginPage.getEmailErrorMessage(), "Некорректный текст ошибки"),
                () -> Assertions.assertEquals(EMPTY_PASSWORD_ERROR_MESSAGE, loginPage.getPasswordErrorMessage(), "Некорректный текст ошибки")
        );
    }

    @Test
    @DisplayName("Маскировка введенного пароля")
    public void testMaskPassword() {
        final String password = GenerateDataUtil.generatePassword();
        final String fieldType = "password";

        loginPage.fillPassword(password);

        Assertions.assertAll(
                () -> Assertions.assertEquals(fieldType, loginPage.getPasswordFieldType(), "Неверный тип поля"),
                () -> Assertions.assertEquals(password, loginPage.getPasswordFieldValue(), "Значение в поле отличается")
        );
    }

    @Test
    @DisplayName("Отображение введенного пароля")
    public void testShowPassword() {
        final String password = GenerateDataUtil.generatePassword();
        final String fieldType = "text";

        loginPage
                .fillPassword(password)
                .clickShowPasswordIcon();

        Assertions.assertAll(
                () -> Assertions.assertEquals(fieldType, loginPage.getPasswordFieldType(), "Неверный тип поля"),
                () -> Assertions.assertEquals(password, loginPage.getPasswordFieldValue(), "Значение в поле отличается")
        );
    }
}
