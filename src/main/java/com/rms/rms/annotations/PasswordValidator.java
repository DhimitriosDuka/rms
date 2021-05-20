package com.rms.rms.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PasswordValidator implements ConstraintValidator<Password, String> {
    public void initialize(Password constraint) {
    }

    public boolean isValid(String password, ConstraintValidatorContext context) {

        if(Objects.isNull(password)) {
            context.buildConstraintViolationWithTemplate("Password must not be null!").addConstraintViolation();
            return false;
        }

        List<String> errors = new ArrayList<>();

        if(notValidLength(password)) errors.add("Password must be between 8 and 16 characters.");
        if(doesNotContainUppercaseChar(password)) errors.add("Password must contain at least one uppercase character.");
        if(doesNotContainLowercaseChar(password)) errors.add("Password must contain at least one lowercase character.");
        if(doesNotContainDigit(password)) errors.add("Password must contain at least one digit.");
        if(doesNotContainSymbol(password)) errors.add("Password must contain at least one symbol.");

        if(errors.size() != 0) {
            context.buildConstraintViolationWithTemplate(errors.toString()).addConstraintViolation();
            return false;
        }
        return true;
    }

    private boolean notValidLength(String password) {
        return password.length() < 8 || password.length() > 16;
    }

    private boolean doesNotContainUppercaseChar(String password) {
        for(char c : password.toCharArray()) {
            if(Character.isUpperCase(c)) return false;
        }
        return true;
    }

    private boolean doesNotContainLowercaseChar(String password) {
        for(char c : password.toCharArray()) {
            if(Character.isLowerCase(c)) return false;
        }
        return true;
    }

    private boolean doesNotContainDigit(String password) {
        for(char c : password.toCharArray()) {
            if(Character.isDigit(c)) return false;
        }
        return true;
    }

    private boolean doesNotContainSymbol(String password) {
        for(char c : password.toCharArray()) {
            for (char special : "[!@#$%&*()_+=|<>?{}/[]~-.,]".toCharArray()) {
                if(c == special) return false;
            }
        }
        return true;
    }

}
