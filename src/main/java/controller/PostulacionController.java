package controller;

import entities.AuthUser;
import entities.Postulacion;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.PostulacionService;

@Controller
public class PostulacionController {
    
    @Autowired
    public PostulacionService postulacionService;
    

    @RequestMapping(value = "/administracion/postulaciones.htm", method = RequestMethod.GET)
    public String getPostulaciones (HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        AuthUser aU = new AuthUser();
        if (session == null) {
            return "redirect:/login.htm";
        } else {
            aU = (AuthUser)session.getAttribute("loggedUser");
            if (aU == null) {
                return "redirect:/login.htm";
            } else {
                String rol = aU.getRol().toLowerCase();
                if (!rol.equals("administrador") && !rol.equals("alumno")) {
                    return "redirect:/home.htm";
                }
            }
        }
        String token = session.getAttribute("token").toString();
        
        
        ArrayList arr = new ArrayList();
        if (aU.getRol().toLowerCase().equals("alumno")) {
            model.addAttribute("title", "Mis Postulaciones");
            arr = postulacionService.getPostulaciones(token, aU.getId().toString());
        } else {
            model.addAttribute("title", "Postulaciones");
            arr = postulacionService.getPostulaciones(token, null);
        }
        ArrayList<Postulacion> vigentes = new ArrayList();
        ArrayList<Postulacion> finalizadas = new ArrayList();
        if (arr != null && !arr.isEmpty() && arr.size() == 2){
            vigentes = (ArrayList)arr.get(0);
            finalizadas = (ArrayList)arr.get(1);
        }
        
        model.addAttribute("lstPostulaciones", vigentes);
        model.addAttribute("lstPostulacionesFinalizadas", finalizadas);
        return "administracion/postulaciones";
    }
    
    
    @RequestMapping(value = "/administracion/postulaciones.htm", params = { "id", "accept" }, method = RequestMethod.GET)
    public String answerPostulacion (@RequestParam String id, @RequestParam String accept, HttpServletRequest request, 
            RedirectAttributes redir) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "redirect:/login.htm";
        } else {
            AuthUser aU = (AuthUser)session.getAttribute("loggedUser");
            if (aU == null) {
                return "redirect:/login.htm";
            }
            String rol = aU.getRol().toLowerCase();
            if (!rol.equals("administrador") && !rol.equals("alumno")) {
                return "redirect:/home.htm";
            }
        }
        String token = session.getAttribute("token").toString();
        
        String msg = "respondida";
        boolean acc = false;
        if (accept.toLowerCase().equals("true")) {
            acc = true;
            msg = "aceptada";
        } else if (accept.toLowerCase().equals("false")) {
            acc = false;
            msg = "rechazada";
        } else {
            redir.addFlashAttribute("errorMsg", "Ha ocurrido un error al mostrar la postulacion.");
            return "redirect:/administracion/postulaciones.htm";
        }
        
        boolean success = postulacionService.answerPostulacion(token, id, acc);
        if (!success) {
            redir.addFlashAttribute("errorMsg", "Ha ocurrido un error al mostrar la postulacion.");
            return "redirect:/administracion/postulaciones.htm";
        }
        
        redir.addFlashAttribute("msg", "Postulación " + msg + " exitosamente.");
        return "redirect:/administracion/postulaciones.htm";
    }
    
}
