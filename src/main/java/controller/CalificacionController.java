package controller;

import entities.AuthUser;
import entities.Calificacion;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.CalificacionService;

@Controller
public class CalificacionController {
    
    @Autowired
    private CalificacionService calificacionService;
    
    @RequestMapping(value = "/administracion/calificaciones.htm", method = RequestMethod.GET)
    public String getCalificaciones (HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        AuthUser aU;
        if (session == null) {
            return "redirect:/login.htm";
        } else {
            aU = (AuthUser)session.getAttribute("loggedUser");
            if (aU == null) {
                return "redirect:/login.htm";
            } else if (!aU.getRol().equals("CEM") && !aU.getRol().equals("Alumno")) {
                return "redirect:/home.htm";
            }
        }
        model.addAttribute("title", "Calificaciones");
        String token = session.getAttribute("token").toString();
        ArrayList<Calificacion> calificaciones;
        if (aU.getRol().equals("Alumno")) {
            String id_usuario = aU.getId().toString();
            calificaciones = calificacionService.getCalificaciones(token, id_usuario);
        } else {
            calificaciones = calificacionService.getCalificaciones(token);
        }
        
        model.addAttribute("lstCalificaciones", calificaciones);
        return "administracion/calificaciones";
    }
}
