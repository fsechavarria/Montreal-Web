package controller;

import entities.Alumno;
import entities.AuthUser;
import entities.Usuario;
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
import service.AlumnoService;

@Controller
public class AlumnoController {
    
    @Autowired
    private AlumnoService alumnoService;
    
    @RequestMapping(value = "/administracion/programas/alumnos.htm", params={ "id", "nombre" }, method = RequestMethod.GET)
    public String getAlumnosPrograma (@RequestParam("id") String id, @RequestParam("nombre") String nombre, HttpServletRequest request, Model model, RedirectAttributes redir) {
        HttpSession session = request.getSession(false);
        AuthUser aU;
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
        model.addAttribute("title", "Usuarios");
        String token = session.getAttribute("token").toString();
        model.addAttribute("nomb_programa", nombre);
        ArrayList<Alumno> lstAlumnos = alumnoService.getAlumnos(token, id);
        
        session.setAttribute("id_programa", id);
        model.addAttribute("lstAlumnos", lstAlumnos);
       
        return "administracion/usuarios/ver-programa";
    }
}
