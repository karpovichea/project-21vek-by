package by.vek21.api.login;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class LoginResponse {

    public static ValidatableResponse getResponse(RequestSpecification requestSpecification, String body) {
        return given()
                .spec(requestSpecification)
                .body(body)
                .when()
                .post()
                .then()
                .log().all();
    }
}
