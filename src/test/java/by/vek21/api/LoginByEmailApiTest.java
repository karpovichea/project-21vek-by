package by.vek21.api;

import by.vek21.api.login.request.LoginByEmailRequest;
import by.vek21.api.login.response.LoginResponse;
import by.vek21.api.login.response.LoginResponseMessages;
import by.vek21.domain.User;
import by.vek21.util.GenerateUsers;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;

@Epic("API тесты")
@Feature("Авторизация с помощью электронной почты и пароля")
public class LoginByEmailApiTest extends BaseApiTest {

    @BeforeEach
    void setUp() {
        LoginByEmailRequest.initRequestSpecification();
    }

    @Test
    @DisplayName("Авторизация c незарегистрированной электронной почтой")
    public void testLoginWithUnregisteredEmail() {
        logger.info("ЗАПУСК ТЕСТА: Авторизация c незарегистрированной электронной почтой");

        User user = GenerateUsers.getUserWithUnregisteredEmailAndAnyPassword();
        ValidatableResponse response = LoginResponse.getResponse(LoginByEmailRequest.requestSpecification, LoginByEmailRequest.getBody(user));

        response
                .statusCode(200)
                .body("error", equalTo(LoginResponseMessages.UNREGISTERED_EMAIL_ERROR_MESSAGE));

        logger.info("ЗАВЕРШЕНИЕ ТЕСТА: Авторизация c незарегистрированной электронной почтой");
    }

    @Test
    @DisplayName("Авторизация с неверным паролем")
    public void testLoginWithInvalidPassword() {
        logger.info("ЗАПУСК ТЕСТА: Авторизация с неверным паролем");

        User user = GenerateUsers.getUserWithRegisteredEmailAndInvalidPassword();
        ValidatableResponse response = LoginResponse.getResponse(LoginByEmailRequest.requestSpecification, LoginByEmailRequest.getBody(user));

        response
                .statusCode(200)
                .body("error", equalTo(LoginResponseMessages.INVALID_PASSWORD_ERROR_MESSAGE));

        logger.info("ЗАВЕРШЕНИЕ ТЕСТА: Авторизация с неверным паролем");
    }

    @Test
    @DisplayName("Авторизация с незаполненными полями")
    public void testLoginWithEmptyEmailAndPassword() {
        logger.info("ЗАПУСК ТЕСТА: Авторизация с незаполненными полями");

        User user = GenerateUsers.getUserWithEmptyEmailAndPassword();
        ValidatableResponse response = LoginResponse.getResponse(LoginByEmailRequest.requestSpecification, LoginByEmailRequest.getBody(user));

        response.statusCode(404);

        logger.info("ЗАВЕРШЕНИЕ ТЕСТА: Авторизация с незаполненными полями");
    }

    @Test
    @DisplayName("Авторизация с недопустимым форматом электронной почты")
    public void testLoginWithInvalidEmailFormat() {
        logger.info("ЗАПУСК ТЕСТА: Авторизация с недопустимым форматом электронной почты");

        User user = GenerateUsers.getUserWithInValidEmailFormatAndAnyPassword();
        ValidatableResponse response = LoginResponse.getResponse(LoginByEmailRequest.requestSpecification, LoginByEmailRequest.getBody(user));

        response
                .statusCode(200)
                .body("error", equalTo(LoginResponseMessages.INVALID_EMAIL_ERROR_MESSAGE));

        logger.info("ЗАВЕРШЕНИЕ ТЕСТА: Авторизация с недопустимым форматом электронной почты");
    }

    @Test
    @DisplayName("Авторизация с паролем недопустимой длины")
    public void testLoginWithInvalidPasswordLength() {
        logger.info("ЗАПУСК ТЕСТА: Авторизация с паролем недопустимой длины");

        User user = GenerateUsers.getUserWithRegisteredEmailAndInvalidPasswordLength();
        ValidatableResponse response = LoginResponse.getResponse(LoginByEmailRequest.requestSpecification,
                LoginByEmailRequest.getBody(user));

        response
                .statusCode(200)
                .body("error", equalTo(LoginResponseMessages.INVALID_PASSWORD_LENGTH_ERROR_MESSAGE));

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
