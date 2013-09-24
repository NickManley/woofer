package woofer.validator.annotation;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import org.hibernate.validator.constraints.Email;
import woofer.validator.NonRegisteredEmailValidator;

@Email
@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = NonRegisteredEmailValidator.class)
@Documented
public @interface NonRegisteredEmail {

    String message() default "Account with that email address is already registered";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    
}
