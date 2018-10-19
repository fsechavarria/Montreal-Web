package controller;

import entities.AuthUser;
import entities.Programa_Estudio;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.ProgramaService;

@Controller
public class ProgramaController {
    
    @Autowired
    private ProgramaService programaService;
    
    @RequestMapping(value = "/administracion/programas.htm", method = RequestMethod.GET)
    public String getProgramas (HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "redirect:/login.htm";
        } else {
            AuthUser aU = (AuthUser)session.getAttribute("loggedUser");
            if (aU == null) {
                return "redirect:/login.htm";
            }
        }
        model.addAttribute("title", "Programas de Estudio");
        String token = session.getAttribute("token").toString();
        ArrayList<Programa_Estudio> lstProgramas = programaService.getProgramas(token);
        model.addAttribute("lstProgramas", lstProgramas);
        return "administracion/programas";
    }
    
    @RequestMapping(value="/administracion/programas.htm", params = { "id" }, method = RequestMethod.GET)
    public String getPrograma (@RequestParam String id, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "redirect:/login.htm";
        } else {
            AuthUser aU = (AuthUser)session.getAttribute("loggedUser");
            if (aU == null) {
                return "redirect:/login.htm";
            }
        }
        model.addAttribute("title", "Programa de Estudio");
        String token = session.getAttribute("token").toString();
        Programa_Estudio pE = programaService.getPrograma(token, id);
        model.addAttribute("programa", pE);
        session.setAttribute("prog", pE);
        return "administracion/programas/ver";
    }
    
    @InitBinder     
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));   
    }
    
    @RequestMapping(value="/administracion/programas.htm", params = { "id" }, method = RequestMethod.POST)
    public String updatePrograma (@RequestParam String id, HttpServletRequest request, Model model,
            @Valid @ModelAttribute("programa") Programa_Estudio programa, BindingResult result, SessionStatus status, RedirectAttributes redir) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "redirect:/login.htm";
        } else {
            AuthUser aU = (AuthUser)session.getAttribute("loggedUser");
            if (aU == null) {
                return "redirect:/login.htm";
            }
        }
        String token = session.getAttribute("token").toString();
        Programa_Estudio pe = (Programa_Estudio)session.getAttribute("prog");
        if ((!Objects.equals(pe.getId_cel(), programa.getId_cel()) || !Objects.equals(pe.getId_programa(), programa.getId_programa())) 
                || !Objects.equals(pe.getId_cem(), programa.getId_cem())) {
            model.addAttribute("errorMsg", "No se guardaron los cambios ya que algunos datos no coinciden.");
            model.addAttribute("programa", pe);
            return "administracion/programas/ver";
        }
        boolean success = programaService.updatePrograma(token, programa);
        if (!success) {
            model.addAttribute("errorMsg", "Error al guardar los datos.");
            return "administracion/programas/ver";
        }
        redir.addFlashAttribute("msg", "Todos los cambios guardados.");
        return "redirect:/administracion/programas.htm";
    }
    
   @RequestMapping(value="/administracion/programas.htm", params = { "id", "delete" }, method = RequestMethod.GET)
    public String deletePrograma(@RequestParam String id, HttpServletRequest request, RedirectAttributes redir){
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "redirect:/login.htm";
        } else {
            AuthUser aU = (AuthUser)session.getAttribute("loggedUser");
            if (aU == null) {
                return "redirect:/login.htm";
            }
        }
        String token = session.getAttribute("token").toString();
        boolean success = programaService.deletePrograma(token, id);
        
        if (!success) {
            redir.addFlashAttribute("errorMsg", "Ha ocurrido un error al eliminar el programa.");
            return "redirect:/administracion/programas.htm";
        }
        
        redir.addFlashAttribute("msg", "Programa eliminado.");
        return "redirect:/administracion/programas.htm";
    }
}
