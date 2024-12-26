package by.vek21.api;

import by.vek21.api.login.request.LoginByPhoneNumberRequest;
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
@Feature("Авторизация с помощью номера телефона")
public class LoginByPhoneNumberApiTest extends BaseApiTest {

    @BeforeEach
    void setUp() {
        LoginByPhoneNumberRequest.initRequestSpecification();
    }

    @Test
    @DisplayName("Авторизация с незарегистрированным номером телефона")
    public void testLoginWithUnregisteredPhone() {
        logger.info("ЗАПУСК ТЕСТА: Авторизация с незарегистрированным номером телефона");

        User user = GenerateUsers.getUserWithUnregisteredPhoneApi();
        ValidatableResponse response = LoginResponse.getResponse(LoginByPhoneNumberRequest.requestSpecification, LoginByPhoneNumberRequest.getBody(user));

        response
                .statusCode(404)
                .body("errors[0].detail", equalTo(LoginResponseMessages.UNREGISTERED_PHONE_ERROR_MESSAGE));

        logger.info("ЗАВЕРШЕНИЕ ТЕСТА: Авторизация с незарегистрированным номером телефона");
    }

    @Test
    @DisplayName("Авторизация с недопустимым кодом номера телефона")
    public void testLoginWithInvalidPhoneCode() {
        logger.info("ЗАПУСК ТЕСТА: Авторизация с недопустимым кодом номера телефона");

        User user = GenerateUsers.getUserWithInvalidPhoneCodeApi();
        ValidatableResponse response = LoginResponse.getResponse(LoginByPhoneNumberRequest.requestSpecification, LoginByPhoneNumberRequest.getBody(user));

        response
                .statusCode(422)
                .body("errors[0].detail", equalTo(LoginResponseMessages.INVALID_PHONE_CODE_ERROR_MESSAGE));

        logger.info("ЗАВЕРШЕНИЕ ТЕСТА: Авторизация с недопустимым кодом номера телефона");
    }

    @Test
    @DisplayName("Авторизация с недопустимым форматом номера телефона")
    public void testLoginWithInvalidPhone() {
        logger.info("ЗАПУСК ТЕСТА: Авторизация с недопустимым форматом номера телефона");

        User user = GenerateUsers.getUserWithInvalidPhoneFormatApi();
        ValidatableResponse response = LoginResponse.getResponse(LoginByPhoneNumberRequest.requestSpecification, LoginByPhoneNumberRequest.getBody(user));

        response
                .statusCode(422)
                .body("errors[0].detail", equalTo(LoginResponseMessages.INVALID_PHONE_FORMAT_ERROR_MESSAGE));

        logger.info("ЗАВЕРШЕНИЕ ТЕСТА: Авторизация с недопустимым форматом номера телефона");
    }

    @Test
    @DisplayName("Авторизация с незаполненным номером телефона")
    public void testLoginWithEmptyPhone() {
        logger.info("ЗАПУСК ТЕСТА: Авторизация с незаполненным номером телефона");

        User user = GenerateUsers.getUserWithEmptyPhone();
        ValidatableResponse response = LoginResponse.getResponse(LoginByPhoneNumberRequest.requestSpecification, LoginByPhoneNumberRequest.getBody(user));

        response
                .statusCode(422)
                .body("errors[0].detail", equalTo(LoginResponseMessages.EMPTY_PHONE_ERROR_MESSAGE));

        logger.info("ЗАВЕРШЕНИЕ ТЕСТА: Авторизация с незаполненным номером телефона");
    }

    @Test
    @DisplayName("Авторизация с невалидным телом запроса")
    public void testLoginWithInvalidBody() {
        logger.info("ЗАПУСК ТЕСТА: Авторизация с невалидным телом запроса");

        String invalidBody = "{";
        ValidatableResponse response = LoginResponse.getResponse(LoginByPhoneNumberRequest.requestSpecification, invalidBody);

        response.statusCode(415);

        logger.info("ЗАВЕРШЕНИЕ ТЕСТА: Авторизация с невалидным телом запроса");
    }
}
