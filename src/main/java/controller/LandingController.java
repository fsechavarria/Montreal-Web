
package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LandingController {
    
    @RequestMapping(value = "/home.htm", method = RequestMethod.GET)
    public String loginRequest (Model model) {
        model.addAttribute("title", "Home");
        return "landing";
    }
}
