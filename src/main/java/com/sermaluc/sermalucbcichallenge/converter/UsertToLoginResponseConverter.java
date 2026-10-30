package com.sermaluc.sermalucbcichallenge.converter;

import com.sermaluc.sermalucbcichallenge.model.entity.User;
import com.sermaluc.sermalucbcichallenge.model.response.LoginResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UsertToLoginResponseConverter implements Converter<User, LoginResponse> {
    @Override
    public LoginResponse convert(User source) {
        return LoginResponse.builder()
                .email(source.getEmail())
                .token(source.getToken())
                .build();
    }
}
