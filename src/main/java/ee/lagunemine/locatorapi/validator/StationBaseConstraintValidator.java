package ee.lagunemine.locatorapi.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StationBaseConstraintValidator implements ConstraintValidator<StationBaseConstraint, Integer> {
    @Override
    public void initialize(StationBaseConstraint constraintAnnotation) { }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return false;
    }
}
