package com.example.conference_reg.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.TYPE_PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = DateTypeValidator.class)
public @interface DateTypeValidation {
    String message() default "Invalid dateFormate: It should be  'YYYY-MM-DD'  in this formate";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
