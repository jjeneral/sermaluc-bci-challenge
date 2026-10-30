package com.sermaluc.sermalucbcichallenge.controller;

import com.sermaluc.sermalucbcichallenge.fixture.UserFixture;
import com.sermaluc.sermalucbcichallenge.model.entity.User;
import com.sermaluc.sermalucbcichallenge.model.request.UserRequest;
import com.sermaluc.sermalucbcichallenge.model.response.UserResponse;
import com.sermaluc.sermalucbcichallenge.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.ConversionService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    UserController userController;

    @Mock
    UserService userService;

    @Mock
    ConversionService conversionService;

    @Test
    void findAll() {


        when(userService.findAll()).thenReturn(UserFixture.getUserList());
        when(conversionService.convert(any(User.class), eq(UserResponse.class))).thenReturn(UserFixture.getUserResponse());

        List<UserResponse> actual = userController.findAll();
        List<UserResponse> expected = UserFixture.getUserResponseList();

        assertEquals(expected.size(), actual.size());
        assertEquals(expected.get(0).getEmail(), actual.get(0).getEmail());

    }

    @Test
    void findById() {
        when(userService.getUserById(UserFixture.UUID_USER_ID_TEST)).thenReturn(UserFixture.getUser());
        when(conversionService.convert(any(User.class), eq(UserResponse.class))).thenReturn(UserFixture.getUserResponse());

        UserResponse actual = userController.findById(UserFixture.UUID_USER_ID_TEST);
        UserResponse expected = UserFixture.getUserResponse();

        assertNotNull(actual);
        assertEquals(expected.getEmail(), actual.getEmail());

    }

    @Test
    void create() {
        when(conversionService.convert(any(UserRequest.class), eq(User.class))).thenReturn(UserFixture.getUser());
        when(conversionService.convert(any(User.class), eq(UserResponse.class))).thenReturn(UserFixture.getUserResponse());
        when(userService.createUser(any(User.class))).thenReturn(UserFixture.getUser());

        UserResponse actual = userController.create(UserFixture.getUserRequest());
        UserResponse expected = UserFixture.getUserResponse();

        assertNotNull(actual);
        assertEquals(expected.getEmail(), actual.getEmail());
        assertEquals(expected.getPhones().size(), actual.getPhones().size());
        assertEquals(expected.getPhones().get(0), actual.getPhones().get(0));

    }

    @Test
    void update() {
        when(conversionService.convert(any(UserRequest.class), eq(User.class))).thenReturn(UserFixture.getUser());
        when(conversionService.convert(any(User.class), eq(UserResponse.class))).thenReturn(UserFixture.getUserResponse());
        when(userService.updateUser(any(User.class), eq(UserFixture.UUID_USER_ID_TEST))).thenReturn(UserFixture.getUser());

        UserResponse actual = userController.update(UserFixture.getUserRequest(), UserFixture.UUID_USER_ID_TEST);
        UserResponse expected = UserFixture.getUserResponse();

        assertNotNull(actual);
        assertEquals(expected.getEmail(), actual.getEmail());
        assertEquals(expected.getPhones().size(), actual.getPhones().size());
        assertEquals(expected.getPhones().get(0), actual.getPhones().get(0));

    }
}