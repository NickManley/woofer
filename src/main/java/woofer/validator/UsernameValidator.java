package woofer.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import woofer.validator.annotation.Username;

public class UsernameValidator
        implements ConstraintValidator<Username, String> {

    @Override
    public void initialize(Username constraintAnnotation) {
       
    }
    
    @Override
    public boolean isValid(String object, 
            ConstraintValidatorContext constraintContext) {
        // "\w" means characters a-z, A-Z, 0-9, and underscores
        // "++" means one or more occurances of that pattern
        return object.matches("\\w++") &&
                !object.equalsIgnoreCase("admin") &&
                !object.equalsIgnoreCase("default");
    }
}
