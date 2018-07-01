package ee.lagunemine.locatorapi.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {StationBaseExistsValidator.class})
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface StationBaseExists {
    String message() default "The requested base station does not exist in database!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
