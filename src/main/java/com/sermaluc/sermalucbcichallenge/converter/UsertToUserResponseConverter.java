package com.sermaluc.sermalucbcichallenge.converter;

import com.sermaluc.sermalucbcichallenge.model.entity.User;
import com.sermaluc.sermalucbcichallenge.model.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UsertToUserResponseConverter implements Converter<User, UserResponse> {
    private final PhoneToPhoneDtoConverter phoneToPhoneDtoConverter;
    @Override
    public UserResponse convert(User source) {
        return UserResponse.builder()
                .userId(source.getUserId())
                .name(source.getName())
                .email(source.getEmail())
                .password(source.getPassword())
                .isactive(source.getIsactive())
                .phones(source.getPhones().stream()
                        .map(phoneToPhoneDtoConverter::convert)
                        .collect(Collectors.toList())
                )
                .created(source.getCreated())
                .modified(source.getModified())
                .lastLogin(source.getLastLogin())
                .token(source.getToken())
                .build();
    }
}
