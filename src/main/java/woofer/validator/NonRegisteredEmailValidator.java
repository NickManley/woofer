package woofer.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import woofer.dao.UserDao;
import woofer.validator.annotation.NonRegisteredEmail;

@Component
public class NonRegisteredEmailValidator
        implements ConstraintValidator<NonRegisteredEmail, String> {
    
    @Autowired
    UserDao userDao;

    @Override
    public void initialize(NonRegisteredEmail constraintAnnotation) {
       
    }
    
    @Override
    public boolean isValid(String email, 
            ConstraintValidatorContext constraintContext) {
        return !userDao.emailRegistered(email);
    }
}
