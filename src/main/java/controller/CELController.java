package controller;

import entities.AuthUser;
import entities.CEL;
import entities.Ciudad;
import entities.Programa_Estudio;
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
import service.CelService;
import service.DireccionService;
import service.ProgramaService;
import service.UsuarioService;

@Controller
public class CELController {
    
    @Autowired
    private CelService celService;
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private ProgramaService programaService;
    
    @Autowired
    private DireccionService direccionService;
    
    @InitBinder     
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));   
    }
    
    @RequestMapping(value = "/administracion/cel/nuevo.htm", method = RequestMethod.GET)
    public String newCEL (HttpServletRequest request, Model model) {
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
        model.addAttribute("title", "Registrar CEL");
        
        String token = session.getAttribute("token").toString();
        
        ArrayList<Ciudad> lstCiudad = direccionService.getCiudades(token);
        
        model.addAttribute("cel", new CEL());
        model.addAttribute("lstCiudad", lstCiudad);
        
        
        return "administracion/cel/nuevo";
    }
    
    @RequestMapping(value = "/administracion/cel/nuevo.htm", method = RequestMethod.POST)
    public String saveCEL (HttpServletRequest request, Model model, @Valid @ModelAttribute("cel") CEL cel, 
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
        model.addAttribute("cel", new CEL());
        
        String message = celService.saveCEL(cel, token);
        if (message != null) {
            redir.addFlashAttribute("errorMsg", message);
            return "redirect:/administracion/usuarios.htm";
        }
        
        redir.addFlashAttribute("msg", "CEL registrado existosamente.");
        return "redirect:/administracion/usuarios.htm";
    }
    
    @RequestMapping(value = "/administracion/mis-programas.htm", method = RequestMethod.GET)
    public String misProgramas(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        AuthUser aU = null;
        if (session == null) {
            return "redirect:/login.htm";
        } else {
            aU = (AuthUser)session.getAttribute("loggedUser");
            if (aU == null) {
                return "redirect:/login.htm";
            } else if (!aU.getRol().equals("CEL")) {
                return "redirect:/home.htm";
            }
        }
        model.addAttribute("title", "Mis Programas");
        String token = session.getAttribute("token").toString();
        
        ArrayList<Programa_Estudio> lstProgramas = programaService.getProgramasCEL(token, aU.getId().toString());
        
        if (lstProgramas == null) {
            model.addAttribute("errorMsg");
            model.addAttribute("lstProgramas", new ArrayList<Programa_Estudio>());
            return "administracion/mis-programas";
        }
        
        model.addAttribute("lstProgramas", lstProgramas);
        return "administracion/mis-programas";
    }
    
}
