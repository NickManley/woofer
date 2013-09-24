package woofer.controller;

import java.util.Date;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import woofer.dao.MessageDao;
import woofer.entity.Message;
import woofer.entity.User;
import woofer.form.MessageForm;

@Controller
public class PostMessageController {
    
    @Autowired
    MessageDao messageDao;
    
    @RequestMapping(value = "/postMessage", method = RequestMethod.POST)
    public String processSubmit(@Valid MessageForm messageForm,
            BindingResult result, Model model, HttpSession session) {
        
        User user = (User) session.getAttribute("user");
        
        if(!result.hasErrors() && user != null) {
            Message message = new Message();
            message.setUser(user);
            message.setText(messageForm.getText());
            message.setPostDate(new Date());
            messageDao.insert(message);
        } else {
            session.setAttribute("invalidWoof", true);
        }
        
        return "redirect:/";
    }
}