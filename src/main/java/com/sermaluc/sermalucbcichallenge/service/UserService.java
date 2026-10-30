package com.sermaluc.sermalucbcichallenge.service;

import com.sermaluc.sermalucbcichallenge.model.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<User> findAll();
    User getUserById(UUID userId);
    User getUserByEmail(String email);
    User createUser(User user);
    User updateUser(User user, UUID userId);
    boolean isEmailAvailable(String email);
}
