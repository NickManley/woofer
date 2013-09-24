package woofer.validator.annotation;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import woofer.validator.AvailableUsernameValidator;

@Username
@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = AvailableUsernameValidator.class)
@Documented
public @interface AvailableUsername {

    String message() default "Username is not available";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    
}
