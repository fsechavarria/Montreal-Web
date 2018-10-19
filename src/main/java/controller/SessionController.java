package controller;

import entities.AuthUser;
import entities.Usuario;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.LoginService;

@Controller
public class SessionController {
    
    @Autowired
    private LoginService loginService;
    
    @RequestMapping(value="/logout.htm", method = RequestMethod.GET)
    public String logoutRequest (HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/login.htm";
    }
    
    @RequestMapping(value="/login.htm", method = RequestMethod.GET)
    public String loginRequest (Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("title", "Login");
        return "login";
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String submit(HttpServletRequest request, @Valid @ModelAttribute("usuario") Usuario usuario, 
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("usuario", new Usuario());
            return "login";
        }
        HttpSession session = request.getSession(true);
        AuthUser loggedUser = null;
        loggedUser = (AuthUser) session.getAttribute("loggedUser");
        if(loggedUser == null) {
            loggedUser = new AuthUser();
        }
        
        String token = loginService.getAuthToken(usuario);
        if (token == null) {
            model.addAttribute("errorMsg", "Usuario y/o contraseña incorrectos.");
            return "login";
        }
        loggedUser = loginService.getUserFromToken(token);
        session.setAttribute("token", token);
        session.setAttribute("loggedUser", loggedUser);
        return "redirect:/home.htm";
    }

}
