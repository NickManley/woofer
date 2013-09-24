package woofer.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import woofer.validator.annotation.FieldMatch;

public class FieldMatchValidator 
        implements ConstraintValidator<FieldMatch, Object> {

    private String firstFieldName;
    private String secondFieldName;
    
    private static final Logger logger =
            Logger.getLogger(FieldMatchValidator.class);

    @Override
    public void initialize(FieldMatch constraintAnnotation) {
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        boolean isMatch;
        try {
            final Object first = BeanUtils.getProperty(value, firstFieldName);
            final Object second = BeanUtils.getProperty(value, secondFieldName);
            if((first == null && second == null) ||
                    (first != null && first.equals(second)) ) {
                return true;
            } else {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(
                        "Fields do not match")
                    .addPropertyNode(secondFieldName)
                    .addConstraintViolation();
                return false;
            }
        } catch (Exception e) {
            logger.debug("$$ error matching fields");
            logger.debug(e.fillInStackTrace());
            return false;
        }
    }
}