package com.example.conference_reg.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.TYPE_PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = EventTypeValidator.class)
public @interface EventTypeValidation {
    String message() default "Invalid eventType: It should be seminar or workshop or conference";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
