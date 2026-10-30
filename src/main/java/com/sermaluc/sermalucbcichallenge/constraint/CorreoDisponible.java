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
@Constraint(validatedBy = CorreoDisponibleValidator.class)
public @interface CorreoDisponible {
    String message() default "El correo ya se encuentra registrado";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
