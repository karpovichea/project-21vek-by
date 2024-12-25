package by.vek21.util;

import by.vek21.domain.User;

public class GenerateUsers {

    private static final String EMPTY_VALUE = "";
    private static final String REGISTERED_EMAIL = "email@gmail.com";
    private static final String UNREGISTERED_EMAIL = "unregistred@gmail.com";
    private static final String INVALID_EMAIL_FORMAT = "email.gmail.com";
    private static final String INVALID_PASSWORD = "111111";
    private static final String INVALID_PASSWORD_LENGTH = "!!!12";
    private static final String UNREGISTERED_PHONE_FOR_API = "+375 (29) 1111111";
    private static final String INVALID_PHONE_CODE_FOR_API = "+375 (55) 1111111";
    private static final String INVALID_PHONE_FORMAT_FOR_API = "+375291111111";
    private static final String UNREGISTERED_PHONE_FOR_UI = "291111111";
    private static final String INVALID_PHONE_CODE_FOR_UI = "551111111";

    public static User getUserWithUnregisteredEmailAndAnyPassword() {
        String anyPassword = GenerateDataUtil.generatePassword();
        return new User(UNREGISTERED_EMAIL, anyPassword);
    }

    public static User getUserWithRegisteredEmailAndInvalidPassword() {
        return new User(REGISTERED_EMAIL, INVALID_PASSWORD);
    }

    public static User getUserWithEmptyEmailAndPassword() {
        return new User(EMPTY_VALUE, EMPTY_VALUE);
    }

    public static User getUserWithAnyEmailAndEmptyPassword() {
        String anyEmail = GenerateDataUtil.generateEmail();
        return new User(anyEmail, EMPTY_VALUE);
    }

    public static User getUserWithEmptyEmailAndAnyPassword() {
        String anyPassword = GenerateDataUtil.generatePassword();
        return new User(EMPTY_VALUE, anyPassword);
    }

    public static User getUserWithInValidEmailFormatAndAnyPassword() {
        String anyPassword = GenerateDataUtil.generatePassword();
        return new User(INVALID_EMAIL_FORMAT, anyPassword);
    }

    public static User getUserWithRegisteredEmailAndInvalidPasswordLength() {
        return new User(REGISTERED_EMAIL, INVALID_PASSWORD_LENGTH);
    }

    public static User getUserWithUnregisteredPhoneApi() {
        return new User(UNREGISTERED_PHONE_FOR_API);
    }

    public static User getUserWithInvalidPhoneCodeApi() {
        return new User(INVALID_PHONE_CODE_FOR_API);
    }

    public static User getUserWithInvalidPhoneFormatApi() {
        return new User(INVALID_PHONE_FORMAT_FOR_API);
    }

    public static User getUserWithUnregisteredPhoneUi() {
        return new User(UNREGISTERED_PHONE_FOR_UI);
    }

    public static User getUserWithInvalidPhoneCodeUi() {
        return new User(INVALID_PHONE_CODE_FOR_UI);
    }

    public static User getUserWithEmptyPhone() {
        return new User(EMPTY_VALUE);
    }
}
