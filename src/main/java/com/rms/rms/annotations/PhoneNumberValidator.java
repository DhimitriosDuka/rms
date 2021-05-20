package com.rms.rms.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {
    public void initialize(PhoneNumber constraint) {
    }

    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        return Objects.nonNull(phoneNumber)
                && phoneNumber.matches("(0|(\\+|00)355)6([789])[0-9]{7,}")
                && phoneNumber.length() >= 10
                && phoneNumber.length() <= 13;
    }
}
