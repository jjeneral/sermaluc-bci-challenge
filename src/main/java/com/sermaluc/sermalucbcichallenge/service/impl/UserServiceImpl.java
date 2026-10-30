package com.sermaluc.sermalucbcichallenge.service.impl;

import com.sermaluc.sermalucbcichallenge.helper.UserHelper;
import com.sermaluc.sermalucbcichallenge.model.entity.User;
import com.sermaluc.sermalucbcichallenge.repository.UserRepository;
import com.sermaluc.sermalucbcichallenge.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService, UserDetailsService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(UUID userId) {
        return findUser(userId);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    @Override
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setIsactive(true);
        return userRepository.save(user);
    }

    @Transactional()
    @Override
    public User updateUser(User user, UUID userId) {
        User userToUpdate = findUser(userId);
        UserHelper.copyUserAttrs(user, userToUpdate);

        return userRepository.save(userToUpdate);
    }

    @Override
    public boolean isEmailAvailable(String email) {
        return !userRepository.existsByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(EntityNotFoundException::new);
        return org.springframework.security.core.userdetails.User.builder()
                .username(email)
                .password(user.getPassword())
                .authorities("USER_ROLE")
                .build();
    }

    private User findUser(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Usuario con id " + userId + " no encontrado"));
    }

}
