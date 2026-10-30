package com.sermaluc.sermalucbcichallenge.service;

import com.sermaluc.sermalucbcichallenge.model.entity.User;
import com.sermaluc.sermalucbcichallenge.model.request.LoginRequest;

public interface AuthenticationService {
    User login(LoginRequest login);
}