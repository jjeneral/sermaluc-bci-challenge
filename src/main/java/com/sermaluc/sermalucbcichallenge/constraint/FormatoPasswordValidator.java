package com.sermaluc.sermalucbcichallenge.constraint;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class FormatoPasswordValidator implements ConstraintValidator<FormatoPassword, String> {
    @Value("${config.formato-password.regex}")
    private String FORMATO_PASSWORD_REGEX;
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (FORMATO_PASSWORD_REGEX == null || FORMATO_PASSWORD_REGEX.isEmpty()) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("Formato de password no configurado")
                    .addConstraintViolation();
        }

        return Pattern.matches(FORMATO_PASSWORD_REGEX, s);
    }
}
