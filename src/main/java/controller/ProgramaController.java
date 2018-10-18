package controller;

import entities.AuthUser;
import entities.Programa_Estudio;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
        
        System.out.println("ID is: " + id);
       
        return "administracion/programas/ver";
    }
}
