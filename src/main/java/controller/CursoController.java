package controller;

import entities.AuthUser;
import entities.Curso;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.CursoService;


@Controller
public class CursoController {
    
    @Autowired
    public CursoService cursoService;
    
    @RequestMapping(value = "/administracion/cursos.htm", method = RequestMethod.GET)
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
        model.addAttribute("title", "Cursos");
        String token = session.getAttribute("token").toString();
        
        ArrayList<Curso> lstCursos = cursoService.getCursos(token);
        
        return "administracion/programas";
    }
}
