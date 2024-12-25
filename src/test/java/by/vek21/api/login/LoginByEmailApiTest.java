package by.vek21.api.login;

import by.vek21.api.BaseApiTest;
import by.vek21.domain.User;
import by.vek21.ui.util.GenerateDataUtil;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;

public class LoginByEmailApiTest extends BaseApiTest {

    private static final String EMPTY_VALUE = "";

    @BeforeEach
    void setUp() {
        LoginByEmailRequest.initRequestSpecification();
    }

    @Test
    @DisplayName("Авторизация c незарегистрированной электронной почтой")
    public void testLoginWithUnregisteredEmail() {
        logger.info("ЗАПУСК ТЕСТА: Авторизация c незарегистрированной электронной почтой");

        String unregisteredEmail = "unregistred@gmail.com";
        String password = GenerateDataUtil.generatePassword();
        User user = new User(unregisteredEmail, password);

        ValidatableResponse response = LoginResponse.getResponse(LoginByEmailRequest.requestSpecification, LoginByEmailRequest.getBody(user));

        response
                .statusCode(200)
                .body("error", equalTo(LoginMessages.UNREGISTERED_EMAIL_ERROR_MESSAGE));

        logger.info("ЗАВЕРШЕНИЕ ТЕСТА: Авторизация c незарегистрированной электронной почтой");
    }

    @Test
    @DisplayName("Авторизация с неверным паролем")
    public void testLoginWithInvalidPassword() {
        logger.info("ЗАПУСК ТЕСТА: Авторизация с неверным паролем");

        String registeredEmail = "email@gmail.com";
        String invalidPassword = GenerateDataUtil.generatePassword();
        User user = new User(registeredEmail, invalidPassword);

        ValidatableResponse response = LoginResponse.getResponse(LoginByEmailRequest.requestSpecification, LoginByEmailRequest.getBody(user));

        response
                .statusCode(200)
                .body("error", equalTo(LoginMessages.INVALID_PASSWORD_ERROR_MESSAGE));

        logger.info("ЗАВЕРШЕНИЕ ТЕСТА: Авторизация с неверным паролем");
    }

    @Test
    @DisplayName("Авторизация с незаполненными полями")
    public void testLoginWithEmptyEmailAndPassword() {
        logger.info("ЗАПУСК ТЕСТА: Авторизация с незаполненными полями");

        User user = new User(EMPTY_VALUE, EMPTY_VALUE);

        ValidatableResponse response = LoginResponse.getResponse(LoginByEmailRequest.requestSpecification, LoginByEmailRequest.getBody(user));

        response.statusCode(404);

        logger.info("ЗАВЕРШЕНИЕ ТЕСТА: Авторизация с незаполненными полями");
    }

    @Test
    @DisplayName("Авторизация с недопустимым форматом электронной почты")
    public void testLoginWithInvalidEmailFormat() {
        logger.info("ЗАПУСК ТЕСТА: Авторизация с недопустимым форматом электронной почты");

        String invalidEmailFormat = "email.gmail.com";
        String password = GenerateDataUtil.generatePassword();
        User user = new User(invalidEmailFormat, password);

        ValidatableResponse response = LoginResponse.getResponse(LoginByEmailRequest.requestSpecification, LoginByEmailRequest.getBody(user));

        response
                .statusCode(200)
                .body("error", equalTo(LoginMessages.INVALID_EMAIL_ERROR_MESSAGE));

        logger.info("ЗАВЕРШЕНИЕ ТЕСТА: Авторизация с недопустимым форматом электронной почты");
    }

    @Test
    @DisplayName("Авторизация с паролем недопустимой длины")
    public void testLoginWithInvalidPasswordFormat() {
        logger.info("ЗАПУСК ТЕСТА: Авторизация с паролем недопустимой длины");

        String registeredEmail = "email@gmail.com";
        String invalidPasswordLength = "!!!12";
        User user = new User(registeredEmail, invalidPasswordLength);

        ValidatableResponse response = LoginResponse.getResponse(LoginByEmailRequest.requestSpecification, LoginByEmailRequest.getBody(user));

        response
                .statusCode(200)
                .body("error", equalTo(LoginMessages.INVALID_PASSWORD_LENGTH_ERROR_MESSAGE));

        logger.info("ЗАВЕРШЕНИЕ ТЕСТА: Авторизация с паролем недопустимой длины");
    }

    @Test
    @DisplayName("Авторизация с невалидным телом запроса")
    public void testLoginWithInvalidBody() {
        logger.info("ЗАПУСК ТЕСТА: Авторизация с невалидным телом запроса");

        String invalidBody = "{";

        ValidatableResponse response = LoginResponse.getResponse(LoginByEmailRequest.requestSpecification, invalidBody);

        response.statusCode(404);

        logger.info("ЗАВЕРШЕНИЕ ТЕСТА: Авторизация с невалидным телом запроса");
    }
}
