package com.sermaluc.sermalucbcichallenge.constraint;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

@Target( { FIELD, PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = FormatoPasswordValidator.class)
public @interface FormatoPassword {
    String message() default "Formato password no válido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
