package woofer.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import woofer.dao.UserDao;
import woofer.validator.annotation.AvailableUsername;

@Component
public class AvailableUsernameValidator
        implements ConstraintValidator<AvailableUsername, String> {
    
    @Autowired
    UserDao userDao;

    @Override
    public void initialize(AvailableUsername constraintAnnotation) {
       
    }
    
    @Override
    public boolean isValid(String username, 
            ConstraintValidatorContext constraintContext) {
        return !userDao.userExists(username);
    }
}
