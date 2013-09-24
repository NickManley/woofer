package woofer.form;

import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

@Component
public class MessageForm {
    
    @NotBlank
    @Size(min = 2, max = 200)
    private String text;
    
    public String getText() {
        return text;
    }
    
    public void setText(String text) {
        this.text = text.replace("\n", " ");
    }
}
