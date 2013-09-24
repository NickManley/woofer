package woofer.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.http.HttpSession;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import woofer.WooferConfig;
import woofer.entity.User;

@Controller
public class ChangeAvatarController {
    
    private static final Logger logger =
            Logger.getLogger(ChangeAvatarController.class);
    
    @Autowired
    WooferConfig wooferConfig;
    
    @RequestMapping(value = "/changeAvatar", method = RequestMethod.GET)
    public String handleRequest(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if(user == null) {
           return "redirect:/"; 
        }
        model.addAttribute("user", user);
        return "changeAvatar";
    }

    @RequestMapping(value = "/changeAvatar", method = RequestMethod.POST)
    public String handleUpload(HttpSession session, WebRequest request,
            @RequestParam(value = "avatar", required = false)
            MultipartFile multipartFile, Model model) {

        // Which user uploaded the file, or if they arne't logged in
        // redirect them to home page.
        User user = (User) session.getAttribute("user");
        if(user == null) {
            model.addAttribute("uploadError",
                    "Error: must be logged in to change avatar"); 
            return "changeAvatar";
        }
        
        String fileType = multipartFile.getContentType();
        if(!(fileType.equalsIgnoreCase("image/jpeg") ||
                fileType.equalsIgnoreCase("image/png"))) {
            model.addAttribute("uploadError",
                    "Error: Invalid file type. Image must be JPEG or PNG");
            return "changeAvatar";
        }
        
        if(multipartFile.getSize() > 1024*1024) {
            model.addAttribute("uploadError",
                    "Error: File size must be <= 1 MB");
            return "changeAvatar";
        }

        // Write the file to disk
        String filePath = wooferConfig.getFileUploadPath() + user.getUsername() + ".jpg";
        File dest = new File(filePath);
        try {
            multipartFile.transferTo(dest);
        } catch (IllegalStateException e) {
            logger.error(e.getMessage(), e.fillInStackTrace());
            model.addAttribute("uploadError", "Fatal: File upload failed");
            return "changeAvatar";
        } catch (IOException e) {
            logger.error(e.getMessage(), e.fillInStackTrace());
            model.addAttribute("uploadError", "Fatal: File upload failed");
            return "changeAvatar";
        }
        
        // Resize the file
        try {
            Thumbnails.of(dest)
                    .size(50, 50)
                    .outputFormat("jpg")
                    .outputQuality(1.00f)
                    .toFile(dest);
        } catch(IOException e) {
            dest.delete();
            logger.fatal(e.getMessage(), e.fillInStackTrace());
            model.addAttribute("uploadError", "Fatal: File upload failed");
            return "changeAvatar";
        }
   
        return "redirect:/";
    }
}