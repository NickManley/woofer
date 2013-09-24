package woofer.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import woofer.WooferConfig;
import woofer.service.AvatarService;

@Controller
public class AvatarController {
 
    @Autowired
    WooferConfig wooferConfig;
    @Autowired
    AvatarService avatarService;
    
    private static final Logger logger =
            Logger.getLogger(AvatarController.class);
    
    @ResponseBody
    @RequestMapping(value = "/avatar/{username}", method = RequestMethod.GET,
            produces = MediaType.IMAGE_JPEG_VALUE )
    public byte[] handleRequest(WebRequest request,
            @PathVariable("username") String username) {
        
        if(!username.matches("\\w++")) {
            return null;
        }
        
        if(avatarService.hasAvatar(username)) {
           return avatarService.getAvatar(username);
        }
        
        return avatarService.getAvatar();
    }
    
}
