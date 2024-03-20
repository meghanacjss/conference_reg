package com.example.conference_reg.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.TYPE_PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = AffilliationTypeValidator.class)
public @interface AffiliationTypeValidation {

    String message() default "Invalid affiliationType: It should be college or institute or school";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
