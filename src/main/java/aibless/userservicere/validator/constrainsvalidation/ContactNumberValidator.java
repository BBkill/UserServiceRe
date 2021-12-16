package aibless.userservicere.validator.constrainsvalidation;

import aibless.userservicere.dto.UserRequestDto;
import aibless.userservicere.validator.constrain.ContactNumberConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ContactNumberValidator implements ConstraintValidator<ContactNumberConstraint, String> {

    @Override
    public void initialize(ContactNumberConstraint contactNumber) {
    }

    @Override
    public boolean isValid(String number, ConstraintValidatorContext constraintValidatorContext) {
        return number != null && number.matches("[0-9]+") && (number.length() > 8) && (number.length() < 14);
    }

}
