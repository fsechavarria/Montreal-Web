
package controller;

import entities.AuthUser;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LandingController {
    
    @RequestMapping(value = "/home.htm", method = RequestMethod.GET)
    public String loginRequest (HttpServletRequest request, Model model) {
        // Se verifica si la sesion existe, en caso contrario se redirecciona al login.
        HttpSession session = request.getSession(false); // False evita que se cree una nueva sesion si esta no existe
        if (session == null) {
            return "redirect:/login.htm";
        } else {
            AuthUser aU = (AuthUser)session.getAttribute("loggedUser");
            if (aU == null) {
                return "redirect:/login.htm";
            }
        }
        
        model.addAttribute("title", "Inicio");
        return "landing";
    }
}
