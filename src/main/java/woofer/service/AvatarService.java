package woofer.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import woofer.WooferConfig;

@Service
public class AvatarService {
    
    @Autowired
    WooferConfig wooferConfig;
    
    private static final String JPG = ".jpg";
    private static final String PNG = ".png";
    
    private static final Logger logger =
            Logger.getLogger(AvatarService.class);
    
    public boolean hasAvatar(String username) {
        String filePath = wooferConfig.getFileUploadPath() + username + ".jpg";
        File imgFile = new File(filePath);
        return imgFile.exists();
    }
    
    public byte[] getAvatar(String username) {
        return getAvatar(username, JPG);
    }
    
    public byte[] getAvatar() {
        return getAvatar("default", PNG);
    }
    
    public byte[] getAvatar(String name, String ext) {
        String filePath = wooferConfig.getFileUploadPath() + name + ext;
        InputStream in;
        try {
             in = new FileInputStream(new File(filePath));
        } catch(FileNotFoundException e) {
            logger.fatal(e.getMessage(), e.fillInStackTrace());
            return null;
        }
        try {
            return IOUtils.toByteArray(in);
        } catch(IOException e) {
            logger.fatal(e.getMessage(), e.fillInStackTrace());
            return null;
        }
    }
    
}
