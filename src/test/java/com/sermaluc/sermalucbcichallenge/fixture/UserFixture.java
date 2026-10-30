package com.sermaluc.sermalucbcichallenge.fixture;

import com.sermaluc.sermalucbcichallenge.model.entity.User;
import com.sermaluc.sermalucbcichallenge.model.request.UserRequest;
import com.sermaluc.sermalucbcichallenge.model.response.UserResponse;

import java.util.List;
import java.util.UUID;

public class UserFixture {
    public static final UUID UUID_USER_ID_TEST = UUID.fromString("952aa7c4-29df-4e12-b288-4d77d219c494");
    public static final String EMAIL_TEST = "juan@rodriguez.org";
    public static final String PASSWORD_TEST = "hunter2";

    public static List<UserResponse> getUserResponseList() {
        return List.of(getUserResponse());
    }

    public static UserResponse getUserResponse() {
        return UserResponse.builder()
                .userId(UUID_USER_ID_TEST)
                .name("Juan Rodriguez")
                .email(EMAIL_TEST)
                .password(PASSWORD_TEST)
                .phones(List.of(PhoneFixture.getPhoneDto()))
                .build();

    }

    public static List<User> getUserList() {
        return List.of(getUser());
    }

    public static User getUser() {
        return User.builder()
                .userId(UUID_USER_ID_TEST)
                .name("Juan Rodriguez")
                .email(EMAIL_TEST)
                .password(PASSWORD_TEST)
                .phones(List.of(PhoneFixture.getPhone()))
                .build();
    }

    public static UserRequest getUserRequest() {
        UserResponse userResponse = getUserResponse();
        return UserRequest.builder()
                .name(userResponse.getName())
                .email(userResponse.getEmail())
                .password(userResponse.getPassword())
                .phones(userResponse.getPhones())
                .build();
    }


}
