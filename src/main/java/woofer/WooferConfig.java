package woofer;

import org.springframework.stereotype.Component;

@Component
public class WooferConfig {
    private String fileUploadPath;
    
    public String getFileUploadPath() {
        return fileUploadPath;
    }
    
    public void setFileUploadPath(String fileUploadPath) {
        this.fileUploadPath = fileUploadPath;
    }
}
