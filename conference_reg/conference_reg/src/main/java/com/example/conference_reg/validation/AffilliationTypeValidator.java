package com.example.conference_reg.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class AffilliationTypeValidator implements ConstraintValidator<AffiliationTypeValidation,String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        List<String> affiliationTypes = Arrays.asList("college","institute","school");
        return affiliationTypes.contains(s);
    }
}
