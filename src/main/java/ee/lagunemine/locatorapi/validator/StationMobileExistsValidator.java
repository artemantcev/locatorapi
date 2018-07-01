package ee.lagunemine.locatorapi.validator;

import ee.lagunemine.locatorapi.model.StationMobile;
import ee.lagunemine.locatorapi.repository.StationMobileRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class StationMobileExistsValidator implements ConstraintValidator<StationMobileExists, Integer> {
    private StationMobileRepository repository;

    @Autowired
    public StationMobileExistsValidator(StationMobileRepository repository) {
        this.repository = repository;
    }

    @Override
    public void initialize(StationMobileExists constraintAnnotation) { }

    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext context) {
        if (id == null) {
            return false;
        }

        Optional<StationMobile> result = repository.findById(id);

        return result.isPresent();
    }
}
