package by.vek21.api.login;

import by.vek21.api.BaseApiTest;
import by.vek21.ui.model.User;
import by.vek21.ui.util.GenerateDataUtil;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;

public class LoginByEmailApiTest extends BaseApiTest {

    private static final String EMPTY_VALUE = "";
    private static final String UNREGISTERED_EMAIL_ERROR_MESSAGE = "Проверьте email";
    private static final String INVALID_PASSWORD_ERROR_MESSAGE = "Неправильный пароль";
    private static final String INVALID_EMAIL_ERROR_MESSAGE = "Ошибка валидации поля email";
    private static final String INVALID_PASSWORD_LENGTH_ERROR_MESSAGE = "Длина поля password должна быть от 6 до 32 символов";

    @BeforeEach
    void setUp() {
        LoginByEmailRequest.initRequestSpecification();
    }

    @Test
    @DisplayName("Авторизация c незарегистрированной электронной почтой")
    public void testLoginWithUnregisteredEmail() {
        String unregisteredEmail = "unregistred@gmail.com";
        String password = GenerateDataUtil.generatePassword();

        User user = new User(unregisteredEmail, password);

        ValidatableResponse response = LoginResponse.getResponse(LoginByEmailRequest.requestSpecification, LoginByEmailRequest.getBody(user));

        response
                .statusCode(200)
                .body("error", equalTo(UNREGISTERED_EMAIL_ERROR_MESSAGE));
    }

    @Test
    @DisplayName("Авторизация с неверным паролем")
    public void testLoginWithIncorrectPassword() {
        String registeredEmail = "email@gmail.com";
        String invalidPassword = GenerateDataUtil.generatePassword();

        User user = new User(registeredEmail, invalidPassword);

        ValidatableResponse response = LoginResponse.getResponse(LoginByEmailRequest.requestSpecification, LoginByEmailRequest.getBody(user));

        response
                .statusCode(200)
                .body("error", equalTo(INVALID_PASSWORD_ERROR_MESSAGE));
    }

    @Test
    @DisplayName("Авторизация с незаполненными полями")
    public void testLoginWithEmptyEmailAndPassword() {
        User user = new User(EMPTY_VALUE, EMPTY_VALUE);

        ValidatableResponse response = LoginResponse.getResponse(LoginByEmailRequest.requestSpecification, LoginByEmailRequest.getBody(user));

        response.statusCode(404);
    }

    @Test
    @DisplayName("Авторизация с недопустимым форматом электронной почты")
    public void testLoginWithInvalidEmailFormat() {
        String invalidEmailFormat = "email.gmail.com";
        String password = GenerateDataUtil.generatePassword();

        User user = new User(invalidEmailFormat, password);

        ValidatableResponse response = LoginResponse.getResponse(LoginByEmailRequest.requestSpecification, LoginByEmailRequest.getBody(user));

        response
                .statusCode(200)
                .body("error", equalTo(INVALID_EMAIL_ERROR_MESSAGE));
    }

    @Test
    @DisplayName("Авторизация с паролем недопустимой длины")
    public void testLoginWithInvalidPasswordFormat() {
        String registeredEmail = "email@gmail.com";
        String invalidPasswordLength = "!!!12";

        User user = new User(registeredEmail, invalidPasswordLength);

        ValidatableResponse response = LoginResponse.getResponse(LoginByEmailRequest.requestSpecification, LoginByEmailRequest.getBody(user));

        response
                .statusCode(200)
                .body("error", equalTo(INVALID_PASSWORD_LENGTH_ERROR_MESSAGE));
    }

    @Test
    @DisplayName("Авторизация с невалидным телом запроса")
    public void testLoginWithInvalidBody() {
        String invalidBody = "{";

        ValidatableResponse response = LoginResponse.getResponse(LoginByEmailRequest.requestSpecification, invalidBody);

        response.statusCode(404);
    }
}
