package woofer.controller;

import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import woofer.dao.MessageDao;
import woofer.entity.User;

@Controller
public class IndexController {
    
    private static final Logger logger = 
            Logger.getLogger(IndexController.class);
    
    @Autowired
    MessageDao messageDao;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String handleRequest(Model model, HttpSession session,
            @RequestParam(value = "query", required = false) String query) {
        
        // Assign user to the model if a user is logged in.
        User user = (User) session.getAttribute("user");
        if(user != null) {
            model.addAttribute("user", user);
            model.addAttribute("messageCount",
                    messageDao.getMessageCount(user));
            model.addAttribute("isIndexOrOwnProfile", true);
        }
        
        if(query != null && !query.isEmpty()) {
            model.addAttribute("messages", messageDao.getByQuery(query, 0, 10));
        } else {
            model.addAttribute("messages", messageDao.getMessages(0, 10));
        }
        // Show invalid error if PostMessageController applied it
        model.addAttribute("invalidWoof", session.getAttribute("invalidWoof"));
        // Remove it from session once added to model
        session.removeAttribute("invalidWoof");
        
        return "index";
    }
}