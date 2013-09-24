package woofer.controller;

import java.util.Date;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import woofer.dao.MessageDao;
import woofer.dao.UserDao;
import woofer.entity.User;
import woofer.form.LoginForm;

@Controller
public class LoginController {
    
    @Autowired
    UserDao userDao;
    
    private static final Logger logger = 
            Logger.getLogger(LoginController.class);
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String handleRequest(Model model, HttpSession session) {
        model.addAttribute("accountCreated",
                session.getAttribute("accountCreated"));
        session.removeAttribute("accountCreated");
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String processSubmit(@Valid LoginForm loginForm,
            Model model, HttpSession session) {
        try {
            User user = userDao.getByCredentials(
                    loginForm.getUsername(), loginForm.getPassword());
            user.setLastLogin(new Date());
            userDao.update(user);
            session.setAttribute("user", user);
        } catch(NoResultException e) {
            model.addAttribute("invalidLogin", true);
            return "login";
        }
        return "redirect:/";
    }
}