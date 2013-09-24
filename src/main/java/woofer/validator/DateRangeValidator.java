package woofer.validator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import woofer.validator.annotation.DateRange;

public class DateRangeValidator
        implements ConstraintValidator<DateRange, Date> {
    
    private String startString, endString;

    @Override
    public void initialize(DateRange constraintAnnotation) {
       startString = constraintAnnotation.start();
       endString = constraintAnnotation.end();
    }

    @Override
    public boolean isValid(Date date, ConstraintValidatorContext cvc) {
        try {
            Date startDate = null, endDate = null;
            
            // Dates should be in ISO 8601 format
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            df.setLenient(false);
            
            // Read start date if present
            if(!startString.isEmpty()) {
                startDate = df.parse(startString);
            }
            // Read end date if present
            if(!endString.isEmpty()) {
                endDate = df.parse(endString);
            }
            
            boolean isValidStart = (startDate == null) || 
                    (date.after(startDate) || date.equals(startDate));
            boolean isValidEnd = (endDate == null) || 
                    (date.before(endDate) || date.equals(endDate));
            return isValidStart && isValidEnd;
            
        } catch (ParseException e) {
            return false;
        }
    }
}
