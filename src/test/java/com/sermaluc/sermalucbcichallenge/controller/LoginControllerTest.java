package com.sermaluc.sermalucbcichallenge.controller;

import com.sermaluc.sermalucbcichallenge.fixture.LoginFixture;
import com.sermaluc.sermalucbcichallenge.model.entity.User;
import com.sermaluc.sermalucbcichallenge.model.request.LoginRequest;
import com.sermaluc.sermalucbcichallenge.model.response.LoginResponse;
import com.sermaluc.sermalucbcichallenge.service.AuthenticationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.ConversionService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginControllerTest {

    @InjectMocks
    LoginController loginController;

    @Mock
    AuthenticationService authenticationService;

    @Mock
    ConversionService conversionService;

    @Test
    void login() {


        when(authenticationService.login(any(LoginRequest.class))).thenReturn(LoginFixture.getUser());
        when(conversionService.convert(any(User.class), eq(LoginResponse.class))).thenReturn(LoginFixture.getLoginResponse());

        LoginResponse actual = loginController.login(LoginFixture.getLoginRequest());
        LoginResponse expected = LoginFixture.getLoginResponse();

        assertNotNull(actual);
        assertEquals(expected.getEmail(), actual.getEmail());
        assertEquals(expected.getToken(), actual.getToken());

    }
}