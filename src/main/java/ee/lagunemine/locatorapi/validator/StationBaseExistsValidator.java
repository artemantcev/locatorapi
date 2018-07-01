package ee.lagunemine.locatorapi.validator;

import ee.lagunemine.locatorapi.model.StationBase;
import ee.lagunemine.locatorapi.repository.StationBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

@Component
public class StationBaseExistsValidator implements ConstraintValidator<StationBaseExists, Integer> {
    private StationBaseRepository repository;

    @Autowired
    public StationBaseExistsValidator(StationBaseRepository repository) {
        this.repository = repository;
    }

    @Override
    public void initialize(StationBaseExists constraintAnnotation) { }

    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext context) {
        if (id == null) {
            return false;
        }

        Optional<StationBase> result = repository.findById(id);

        return result.isPresent();
    }
}
