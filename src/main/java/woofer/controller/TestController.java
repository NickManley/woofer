package woofer.controller;

import java.util.Calendar;
import java.util.Locale;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
    
    private static Logger logger =
            Logger.getLogger(TestController.class);
    
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String handleRequest(Model model) {
        return "test";
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @ResponseBody
    public String postResponse(Model model) {
        return "{ \"test\" : \"Hello World!\" }";
    }

    
}
