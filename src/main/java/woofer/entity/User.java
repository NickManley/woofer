package woofer.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;

@Entity
@Table(name = "users")
public class User implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id @NotNull
    @Size(min = 2, max = 20)
    @Column(name = "username")
    private String username;
    
    @NotNull @Email
    @Column(name = "email")
    private String email;
    
    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "birthdate")
    private Date birthdate;
    
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_login")
    private Date lastLogin;
    
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "account_created")
    private Date accountCreated;
    
    @NotNull
    @Size(min = 16, max = 16)
    @Column(name = "salt")
    private String salt;
    
    @NotNull
    @Size(min = 6, max = 63)
    @Column(name = "password")
    private String password;
    
    public User() {
        
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public Date getBirthdate() {
        return birthdate;
    }
    
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
    
    public Date getLastLogin() {
        return lastLogin;
    }
    
    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }
    
    public Date getAccountCreated() {
        return accountCreated;
    }
    
    public void setAccountCreated(Date accountCreated) {
        this.accountCreated = accountCreated;
    }
    
    public String getSalt() {
        return salt;
    }
    
    public void setSalt(String salt) {
        this.salt = salt;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
}
