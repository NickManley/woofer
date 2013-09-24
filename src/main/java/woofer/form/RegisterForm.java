package woofer.form;

import java.util.Date;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import woofer.validator.annotation.AvailableUsername;
import woofer.validator.annotation.DateRange;
import woofer.validator.annotation.FieldMatch;
import woofer.validator.annotation.NonRegisteredEmail;

@Component
@FieldMatch(first = "password", second = "confirmPassword",
        message = "passwords must match")
public class RegisterForm {
    
    @AvailableUsername
    private String username;
    
    @Size(min = 3, max = 63)
    private String password;

    private String confirmPassword;
    
    @NonRegisteredEmail
    private String email;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past
    @DateRange(start = "1900-01-01")
    private Date birthDate;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public Date getBirthDate() {
        return birthDate;
    }
    
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Username: ").append(username).append("<br/>")
                .append("Password: ").append(password).append("<br/>")
                .append("Email: ").append(email).append("<br/>")
                .append("BirthDate: ").append(birthDate);
        return sb.toString();
    }
}
