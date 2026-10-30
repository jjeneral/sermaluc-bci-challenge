package com.sermaluc.sermalucbcichallenge.service.impl;

import com.sermaluc.sermalucbcichallenge.model.entity.User;
import com.sermaluc.sermalucbcichallenge.model.request.LoginRequest;
import com.sermaluc.sermalucbcichallenge.repository.UserRepository;
import com.sermaluc.sermalucbcichallenge.security.JwtProvider;
import com.sermaluc.sermalucbcichallenge.service.AuthenticationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Log4j2
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;


    @Override
    public User login(LoginRequest login) {

        User user = userRepository.findByEmail(login.getEmail()).orElseThrow(EntityNotFoundException::new);
        if (!user.getIsactive()) {
            throw new AccessDeniedException("Usuario inactivo");
        }

        org.springframework.security.core.Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(login.getEmail(),
                                login.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        user.setToken(jwtProvider.generateToken(authentication));
        user.setLastLogin(LocalDateTime.now());

        return userRepository.save(user);
    }
}