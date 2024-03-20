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
















/*public class DateTypeValidator implements ConstraintValidator<DateTypeValidation, String> {

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
        return false;
    }
}*/










//public class DateTypeValidator implements ConstraintValidator<DateTypeValidation, String> {
//    private static final String DATE_REGEX = "\\d{4}-\\d{2}-\\d{2}";
//    @Override
//    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
//        if (s == null) {
//            return true;
//        }
//        return Pattern.matches(DATE_REGEX, s);
//    }
//}
