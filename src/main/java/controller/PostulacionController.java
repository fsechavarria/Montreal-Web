package controller;

import entities.AuthUser;
import entities.Curso;
import entities.Postulacion;
import entities.Programa_Estudio;
import java.util.ArrayList;
import java.util.Objects;
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
        
        ArrayList<Postulacion> vigentes = (ArrayList)arr.get(0);
        ArrayList<Postulacion> finalizadas = (ArrayList)arr.get(1);
        
        model.addAttribute("lstPostulaciones", vigentes);
        model.addAttribute("lstPostulacionesFinalizadas", finalizadas);
        return "administracion/postulaciones";
    }
    
    /*
    @RequestMapping(value = "/administracion/cursos.htm", params = { "id" }, method = RequestMethod.GET)
    public String getCurso (@RequestParam String id, HttpServletRequest request, Model model, RedirectAttributes redir) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "redirect:/login.htm";
        } else {
            AuthUser aU = (AuthUser)session.getAttribute("loggedUser");
            if (aU == null) {
                return "redirect:/login.htm";
            }
        }
        model.addAttribute("title", "Curso");
        String token = session.getAttribute("token").toString();
        
        Curso curso = cursoService.getCurso(token, id);
        if (curso == null) {
            redir.addAttribute("errorMsg", "Ha ocurrido un error al mostrar el curso.");
            return "redirect:/administracion/cursos.htm";
        }
        model.addAttribute("curso", curso);
        session.setAttribute("curso", curso);
        
        ArrayList<Programa_Estudio> lstPrograma = programaService.getProgramas(token);
        if (lstPrograma == null) {
            lstPrograma = new ArrayList<>();
        }
        model.addAttribute("lstPrograma", lstPrograma);
        
        
        
        return "administracion/cursos/ver";
    }
    
    @RequestMapping(value = "/administracion/cursos/create.htm", method = RequestMethod.GET)
    public String saveCurso (HttpServletRequest request, Model model) {
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
        model.addAttribute("title", "Nuevo Curso");
        model.addAttribute("curso", new Curso());
        
        ArrayList<Programa_Estudio> lstPrograma = programaService.getProgramas(token);
        if (lstPrograma == null) {
            lstPrograma = new ArrayList<>();
        }
        model.addAttribute("lstPrograma", lstPrograma);
        
        return "administracion/cursos/nuevo";
    }
    
    
    @RequestMapping(value="/administracion/cursos/create.htm", method = RequestMethod.POST)
    public String saveCurso (HttpServletRequest request, Model model, @Valid @ModelAttribute("curso") Curso curso,
            BindingResult result, RedirectAttributes redir) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "redirect:/login.htm";
        } else {
            AuthUser aU = (AuthUser)session.getAttribute("loggedUser");
            if (aU == null) {
                return "redirect:/login.htm";
            }
        }
        
        if (result.hasErrors()){
            return "administracion/cursos/nuevo";
        }
        
        String token = session.getAttribute("token").toString();
        
        boolean success = cursoService.saveCurso(token, curso);
        
        if (!success) {
            redir.addAttribute("errorMsg", "Ha ocurrido un error creando el curso.");
            return "redirect:/administracion/cursos.htm";
        }
        
        redir.addAttribute("msg", "Curso creado exitosamente.");
        return "redirect:/administracion/cursos.htm";
    }
    
    @RequestMapping(value="/administracion/cursos.htm", params = { "id" }, method = RequestMethod.POST)
    public String updateCurso (@RequestParam String id, HttpServletRequest request, Model model,
            @Valid @ModelAttribute("curso") Curso curso, BindingResult result, RedirectAttributes redir) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "redirect:/login.htm";
        } else {
            AuthUser aU = (AuthUser)session.getAttribute("loggedUser");
            if (aU == null) {
                return "redirect:/login.htm";
            }
        }
        if(result.hasErrors()){
            return "administracion/cursos/ver";
        }
        
        Curso cu = (Curso)session.getAttribute("curso");
        if (!Objects.equals(cu.getId_curso(), curso.getId_curso())) {
            redir.addFlashAttribute("errorMsg", "No se guardaron los cambios ya que algunos datos no coinciden.");
            return "redirect:/administracion/cursos.htm";
        }
        
        String token = session.getAttribute("token").toString();
        boolean success = cursoService.updateCurso(token, curso);
        if (!success) {
            model.addAttribute("errorMsg", "Error al guardar los datos.");
            return "administracion/cursos/ver";
        }
        
        redir.addFlashAttribute("msg", "Todos los cambios guardados.");
        return "redirect:/administracion/cursos.htm";
    }
    
    @RequestMapping(value="/administracion/cursos.htm", params = { "id", "delete" }, method = RequestMethod.GET)
    public String deleteCurso(@RequestParam String id, HttpServletRequest request, RedirectAttributes redir){
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
        boolean success = cursoService.deleteCurso(token, id);
        
        if (!success) {
            redir.addFlashAttribute("errorMsg", "Ha ocurrido un error al eliminar el curso.");
            return "redirect:/administracion/cursos.htm";
        }
        
        redir.addFlashAttribute("msg", "Curso eliminado.");
        return "redirect:/administracion/cursos.htm";
    }
    */
}
