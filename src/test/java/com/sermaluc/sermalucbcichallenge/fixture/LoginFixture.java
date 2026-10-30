package com.sermaluc.sermalucbcichallenge.fixture;

import com.sermaluc.sermalucbcichallenge.model.entity.User;
import com.sermaluc.sermalucbcichallenge.model.request.LoginRequest;
import com.sermaluc.sermalucbcichallenge.model.response.LoginResponse;

import java.time.LocalDateTime;

public class LoginFixture {

    public static final String TOKEN_TEST = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqdWFuQHJvZHJpZ3Vlei5vcmciLCJpYXQiOjE3MjQwMzYzMDcsImV4cCI6MTcyNDIwOTEwN30.XQKkuJ5DNsLINPjTYNjPZkEvLsrYDEA93910AoeweidQvLM5rMCZ5xKErgJUuJV87cQ1Gxp77fCjaubryWgtpA";

    public static LoginRequest getLoginRequest() {
        return LoginRequest.builder()
                .email(UserFixture.EMAIL_TEST)
                .password(UserFixture.PASSWORD_TEST)
                .build();
    }

    public static LoginResponse getLoginResponse() {
        return LoginResponse.builder()
                .email(UserFixture.EMAIL_TEST)
                .token(TOKEN_TEST)
                .build();
    }

    public static User getUser() {
        User user = UserFixture.getUser();
        user.setToken(TOKEN_TEST);
        user.setLastLogin(LocalDateTime.now());

        return user;
    }
}
