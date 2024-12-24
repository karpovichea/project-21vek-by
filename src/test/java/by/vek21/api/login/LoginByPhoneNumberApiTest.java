package by.vek21.api.login;

import by.vek21.api.BaseApiTest;
import by.vek21.domain.User;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;

public class LoginByPhoneNumberApiTest extends BaseApiTest {

    private static final String EMPTY_VALUE = "";
    private static final String UNREGISTERED_PHONE_ERROR_MESSAGE = "Пользователь не найден";
    private static final String INVALID_PHONE_FORMAT_ERROR_MESSAGE = "Неправильный формат номера телефона";
    private static final String INVALID_PHONE_CODE_ERROR_MESSAGE = "Телефон должен начинаться с: +375 (25,29,33,44) или +7 (9xx)";
    private static final String EMPTY_PHONE_ERROR_MESSAGE = "Поле phone является обязательным полем";

    @BeforeEach
    void setUp() {
        LoginByPhoneNumberRequest.initRequestSpecification();
    }

    @Test
    @DisplayName("Авторизация с незарегистрированным номером телефона")
    public void testLoginWithUnregisteredPhone() {
        logger.info("ЗАПУСК ТЕСТА: Авторизация с незарегистрированным номером телефона");

        String unregisteredPhone = "+375 (29) 1111111";

        User user = new User(unregisteredPhone);

        ValidatableResponse response = LoginResponse.getResponse(LoginByPhoneNumberRequest.requestSpecification, LoginByPhoneNumberRequest.getBody(user));

        response
                .statusCode(404)
                .body("errors[0].detail", equalTo(UNREGISTERED_PHONE_ERROR_MESSAGE));

        logger.info("ЗАВЕРШЕНИЕ ТЕСТА: Авторизация с незарегистрированным номером телефона");
    }

    @Test
    @DisplayName("Авторизация с недопустимым кодом номера телефона")
    public void testLoginWithInvalidPhoneCode() {
        logger.info("ЗАПУСК ТЕСТА: Авторизация с недопустимым кодом номера телефона");

        String unregisteredPhone = "+375 (55) 1111111";

        User user = new User(unregisteredPhone);

        ValidatableResponse response = LoginResponse.getResponse(LoginByPhoneNumberRequest.requestSpecification, LoginByPhoneNumberRequest.getBody(user));

        response
                .statusCode(422)
                .body("errors[0].detail", equalTo(INVALID_PHONE_CODE_ERROR_MESSAGE));

        logger.info("ЗАВЕРШЕНИЕ ТЕСТА: Авторизация с недопустимым кодом номера телефона");
    }

    @Test
    @DisplayName("Авторизация с недопустимым форматом номера телефона")
    public void testLoginWithInvalidPhone() {
        logger.info("ЗАПУСК ТЕСТА: Авторизация с недопустимым форматом номера телефона");

        String unregisteredPhone = "+375291111111";

        User user = new User(unregisteredPhone);

        ValidatableResponse response = LoginResponse.getResponse(LoginByPhoneNumberRequest.requestSpecification, LoginByPhoneNumberRequest.getBody(user));

        response
                .statusCode(422)
                .body("errors[0].detail", equalTo(INVALID_PHONE_FORMAT_ERROR_MESSAGE));

        logger.info("ЗАВЕРШЕНИЕ ТЕСТА: Авторизация с недопустимым форматом номера телефона");
    }

    @Test
    @DisplayName("Авторизация с незаполненным номером телефона")
    public void testLoginWithEmptyPhone() {
        logger.info("ЗАПУСК ТЕСТА: Авторизация с незаполненным номером телефона");

        User user = new User(EMPTY_VALUE);

        ValidatableResponse response = LoginResponse.getResponse(LoginByPhoneNumberRequest.requestSpecification, LoginByPhoneNumberRequest.getBody(user));

        response
                .statusCode(422)
                .body("errors[0].detail", equalTo(EMPTY_PHONE_ERROR_MESSAGE));

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
