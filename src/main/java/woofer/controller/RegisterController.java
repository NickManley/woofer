package woofer.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import woofer.dao.UserDao;
import woofer.entity.User;
import woofer.form.RegisterForm;
import woofer.service.PasswordEncryptionService;
import woofer.validator.editor.BirthDatePropertyEditor;

@Controller
public class RegisterController {
    
    // Some constants needed for this controller
    private static final String[] monthList = 
            {"Jan", "Feb", "Mar", "Apr", "May", "Jun",
            "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    private static final int startYear = 1900;
    private static final int endYear = Calendar.getInstance(Locale.US)
            .get(Calendar.YEAR);
    
    @Autowired
    PasswordEncryptionService pes;
    @Autowired
    UserDao userDao;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String handleRequest(Model model) {
        initModel(model);
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String processSubmit(
            @Valid RegisterForm registerForm,
            BindingResult result, Model model, HttpSession session) {
        
        if(!result.hasErrors()) {
            final Date NOW = new Date();
            final String SALT = pes.generateSalt();
            User user = new User();
            user.setUsername(registerForm.getUsername());
            user.setEmail(registerForm.getEmail());
            user.setBirthdate(registerForm.getBirthDate());
            user.setLastLogin(NOW);
            user.setAccountCreated(NOW);
            user.setSalt(SALT);
            user.setPassword(pes.getEncryptedPassword(
                    registerForm.getPassword(), SALT));
            userDao.insert(user);
            session.setAttribute("accountCreated", true);
            return "redirect:/login";
        }
        
        initModel(model);
        return "register";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder, 
            WebRequest request) throws Exception {
        binder.registerCustomEditor(Date.class,
                new BirthDatePropertyEditor(request));
    }
    
    /**
     * Adds constants that the model needs to present
     * to the view.
     * @param model 
     */
    public void initModel(Model model) {
        model.addAttribute("monthList", monthList);
        model.addAttribute("startYear", startYear);
        model.addAttribute("endYear", endYear);
    }
}
