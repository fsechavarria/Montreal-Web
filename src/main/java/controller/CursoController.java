package controller;

import entities.AuthUser;
import entities.Curso;
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
import service.CursoService;
import service.ProgramaService;


@Controller
public class CursoController {
    
    @Autowired
    public CursoService cursoService;
    
    @Autowired
    public ProgramaService programaService;
    
    @RequestMapping(value = "/administracion/cursos.htm", method = RequestMethod.GET)
    public String getCursos (HttpServletRequest request, Model model) {
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
        model.addAttribute("lstCursos", lstCursos);
        
        return "administracion/cursos";
    }
    
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
        model.addAttribute("lstPrograma", lstPrograma.get(0));
        
        
        
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
        model.addAttribute("lstPrograma", lstPrograma.get(0));
        
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
        
        String token = session.getAttribute("token").toString();
        
        boolean success = cursoService.saveCurso(token, curso);
        
        if (!success) {
            redir.addFlashAttribute("errorMsg", "Ha ocurrido un error creando el curso.");
            return "redirect:/administracion/cursos.htm";
        }
        
        redir.addFlashAttribute("msg", "Curso creado exitosamente.");
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
}
