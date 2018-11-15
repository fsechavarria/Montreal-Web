package controller;

import entities.AuthUser;
import entities.CEM;
import entities.Ciudad;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.CEMService;
import service.DireccionService;
import service.UsuarioService;


@Controller
public class CEMController {
    
    
    @Autowired
    private CEMService cemService;
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private DireccionService direccionService;
    
    @InitBinder     
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));   
    }
    
    @RequestMapping(value = "/administracion/cem/nuevo.htm", method = RequestMethod.GET)
    public String newCEM (HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "redirect:/login.htm";
        } else {
            AuthUser aU = (AuthUser)session.getAttribute("loggedUser");
            if (aU == null) {
                return "redirect:/login.htm";
            } else if (!aU.getRol().equals("Administrador")) {
                return "redirect:/home.htm";
            }
        }
        model.addAttribute("title", "Registrar CEM");
        
        String token = session.getAttribute("token").toString();
        
        ArrayList<Ciudad> lstCiudad = direccionService.getCiudades(token);
        
        model.addAttribute("cem", new CEM());
        model.addAttribute("lstCiudad", lstCiudad);
        
        
        return "administracion/cem/nuevo";
    }
    
    @RequestMapping(value = "/administracion/cem/nuevo.htm", method = RequestMethod.POST)
    public String saveCEM (HttpServletRequest request, Model model, @Valid @ModelAttribute("cem") CEM cem, 
            BindingResult result, RedirectAttributes redir) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "redirect:/login.htm";
        } else {
            AuthUser aU = (AuthUser)session.getAttribute("loggedUser");
            if (aU == null) {
                return "redirect:/login.htm";
            } else if (!aU.getRol().equals("Administrador")) {
                return "redirect:/home.htm";
            }
        }
        String token = session.getAttribute("token").toString();
        model.addAttribute("cem", new CEM());
        
        String message = cemService.saveCEM(cem, token);
        if (message != null) {
            redir.addFlashAttribute("errorMsg", message);
            return "redirect:/administracion/usuarios.htm";
        }
        
        redir.addFlashAttribute("msg", "CEM registrado existosamente.");
        return "redirect:/administracion/usuarios.htm";
    }
}
