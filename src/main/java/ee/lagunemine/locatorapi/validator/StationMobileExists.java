package ee.lagunemine.locatorapi.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {StationMobileExistsValidator.class})
@Target({ ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface StationMobileExists {
    String message() default "The requested mobile station does not exist in database!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}