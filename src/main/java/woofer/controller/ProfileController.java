package woofer.controller;

import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import woofer.dao.MessageDao;
import woofer.entity.User;

@Controller
public class ProfileController {
    
    private static final Logger logger = 
            Logger.getLogger(ProfileController.class);
    
    @Autowired
    MessageDao messageDao;

    @RequestMapping(value = "/profile/{username}", method = RequestMethod.GET)
    public String handleRequest(@PathVariable("username") String username,
            Model model, HttpSession session) {
        
        // Assign user to the model if a user is logged in.
        User user = (User) session.getAttribute("user");
        if(user != null) {
            model.addAttribute("user", user);
            model.addAttribute("messageCount",
                    messageDao.getMessageCount(user));
            if(user.getUsername().equals(username)) {
                model.addAttribute("isIndexOrOwnProfile", true);
            }
        }
        
        // Get messages
        model.addAttribute("messages", messageDao.getByUsername(username, 0, 10));
        // Show invalid error if PostMessageController applied it
        model.addAttribute("invalidWoof", session.getAttribute("invalidWoof"));
        // Remove it from session once added to model
        session.removeAttribute("invalidWoof");
        
        return "index";
    }
}