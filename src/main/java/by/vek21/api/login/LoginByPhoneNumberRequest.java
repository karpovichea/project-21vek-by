package by.vek21.api.login;

import by.vek21.domain.User;
import com.google.gson.Gson;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class LoginByPhoneNumberRequest {

    public static RequestSpecification requestSpecification;

    public static void initRequestSpecification() {
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://gate.21vek.by")
                .setBasePath("account/users/login/send-confirmation")
                .setContentType("application/json")
                .addHeader("user-agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0.0.0 Mobile Safari/537.36")
                .addHeader("accept", "application/json")
                .build();
    }

    public static String getBody(User user) {
        Gson gson = new Gson();
        return gson.toJson(Map.of("phone", user.getPhone()));
    }
}
