package controller;

import entities.Alumno;
import entities.AuthUser;
import entities.Ciudad;
import entities.Familia;
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
import service.AlumnoService;
import service.DireccionService;
import service.FamiliaService;

@Controller
public class RegisterController {
    
    @Autowired
    private AlumnoService alumnoService;
    
    @Autowired
    private FamiliaService familiaService;
    
    @Autowired
    private DireccionService direccionService;
    
    @InitBinder     
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));   
    }
    
    @RequestMapping(value = "/registrar-alumno.htm", method = RequestMethod.GET)
    public String getRegistrarAlumno (HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            AuthUser aU = session.getAttribute("loggedUser") != null ? (AuthUser)session.getAttribute("loggedUser") : null;
            String token = session.getAttribute("token") != null ? session.getAttribute("token").toString() : null;
            if (aU != null && token != null) {
                return "redirect:/home.htm";
            }
        }
        model.addAttribute("title", "Registro Alumnos");
        model.addAttribute("alumno", new Alumno());
        
        ArrayList<Ciudad> lstCiudades = direccionService.getCiudades();
        model.addAttribute("lstCiudad", lstCiudades);
        
        return "register-alumno";
    }
    
    @RequestMapping(value = "/registrar-alumno.htm", method = RequestMethod.POST)
    public String registrarAlumno (HttpServletRequest request, Model model, @Valid @ModelAttribute("alumno") Alumno alumno, 
            BindingResult result, RedirectAttributes redir) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            AuthUser aU = session.getAttribute("loggedUser") != null ? (AuthUser)session.getAttribute("loggedUser") : null;
            String token = session.getAttribute("token") != null ? session.getAttribute("token").toString() : null;
            if (aU != null && token != null) {
                return "redirect:/home.htm";
            }
        }
        
        String message = alumnoService.saveAlumno(alumno);
        if (message != null) {
            redir.addFlashAttribute("errorMsg", message);
            return "redirect:/login.htm";
        }
        
        redir.addFlashAttribute("msg", "Usuario registrado correctamente.");
        return "redirect:/login.htm";
    }
    
    @RequestMapping(value = "/registrar-familia.htm", method = RequestMethod.GET)
    public String getRegistrarFamilia (HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            AuthUser aU = session.getAttribute("loggedUser") != null ? (AuthUser)session.getAttribute("loggedUser") : null;
            String token = session.getAttribute("token") != null ? session.getAttribute("token").toString() : null;
            if (aU != null && token != null) {
                return "redirect:/home.htm";
            }
        }
        model.addAttribute("title", "Registro Familias");
        model.addAttribute("familia", new Familia());
        
        ArrayList<Ciudad> lstCiudades = direccionService.getCiudades();
        model.addAttribute("lstCiudad", lstCiudades);
        
        return "register-familia";
    }
    
    @RequestMapping(value = "/registrar-familia.htm", method = RequestMethod.POST)
    public String registrarFamilia (HttpServletRequest request, Model model, @Valid @ModelAttribute("familia") Familia familia, 
            BindingResult result, RedirectAttributes redir) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            AuthUser aU = session.getAttribute("loggedUser") != null ? (AuthUser)session.getAttribute("loggedUser") : null;
            String token = session.getAttribute("token") != null ? session.getAttribute("token").toString() : null;
            if (aU != null && token != null) {
                return "redirect:/home.htm";
            }
        }
        
        String message = familiaService.saveFamilia(familia);
        if (message != null) {
            redir.addFlashAttribute("errorMsg", message);
            return "redirect:/login.htm";
        }
        
        redir.addFlashAttribute("msg", "Usuario registrado correctamente.");
        return "redirect:/login.htm";
    }
}
