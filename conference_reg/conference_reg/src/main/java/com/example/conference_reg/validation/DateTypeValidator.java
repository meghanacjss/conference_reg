package com.example.conference_reg.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class DateTypeValidator implements ConstraintValidator<DateTypeValidation, String> {

    @Override
    public boolean isValid(String date, ConstraintValidatorContext context) {
        if (date == null || date.isEmpty()) {
            return true;
        }
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            LocalDate parsedDate = LocalDate.parse(date, dateFormatter);
            LocalDate currntDate = LocalDate.now();
            if (parsedDate.isBefore(currntDate) || parsedDate.isEqual(currntDate)) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}

