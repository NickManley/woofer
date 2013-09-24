package woofer.controller;

import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LogoutController {
    
    private static final Logger logger = 
            Logger.getLogger(LoginController.class);
    
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String handleRequest(Model model, HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/";
    }
}