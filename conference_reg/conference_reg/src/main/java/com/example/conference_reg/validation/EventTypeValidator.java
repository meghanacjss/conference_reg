package com.example.conference_reg.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class EventTypeValidator implements ConstraintValidator<EventTypeValidation,String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        List<String> eventTypes = Arrays.asList("seminar","workshop","conference");
        return eventTypes.contains(s);
    }
}
