package controller;

import entities.AuthUser;
import entities.Calificacion;
import entities.Curso;
import java.util.ArrayList;
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
import service.CalificacionService;
import service.CursoService;

@Controller
public class CalificacionController {
    
    @Autowired
    private CalificacionService calificacionService;
    
    @Autowired
    private CursoService cursoService;
    
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
    
    @RequestMapping(value = "/administracion/calificaciones/alumno.htm", params = { "id", "nombre" }, method = RequestMethod.GET)
    public String getCalificacionesAlumno (@RequestParam("id") String id, @RequestParam("nombre") String nombre, HttpServletRequest request, RedirectAttributes redir, Model model) {
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
        String token = session.getAttribute("token").toString();
        String id_programa = null;
        if (session.getAttribute("id_programa") != null) {
            id_programa = session.getAttribute("id_programa").toString();
        }
        model.addAttribute("title", "Calificaciones");
        model.addAttribute("nomb_alumno", nombre);
        session.setAttribute("nomb_alumno", nombre);
        ArrayList<Calificacion> calificaciones = calificacionService.getCalificacionesAlumno(token, id, id_programa);
        
        
        model.addAttribute("lstCalificaciones", calificaciones);
        return "administracion/calificaciones/ver-alumno";
    }
    
    @RequestMapping(value = "/administracion/calificaciones/nueva.htm", params = {"id"}, method = RequestMethod.GET)
    public String nuevaCalificacion (@RequestParam("id") String id, HttpServletRequest request, Model model) {
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
        model.addAttribute("title", "Nueva Calificación");
        String token = session.getAttribute("token").toString();
        
        String nomb_alumno = null;
        String id_programa = null;
        if (session.getAttribute("nomb_alumno") != null) {
            nomb_alumno = session.getAttribute("nomb_alumno").toString();
        }
        if (session.getAttribute("id_programa") != null) {
            id_programa = session.getAttribute("id_programa").toString();
        }
        ArrayList<Curso> cursos = cursoService.getCursos(token, id_programa);
        
        model.addAttribute("calificacion", new Calificacion());
        model.addAttribute("cursos", cursos);
        session.setAttribute("id_alumno", id);
        model.addAttribute("nomb_alumno", nomb_alumno);
        
        return "administracion/calificaciones/nueva";
    }
    
    @RequestMapping(value="/administracion/calificaciones/nueva.htm", method = RequestMethod.POST)
    public String ingresarCalificacion (HttpServletRequest request, Model model, @Valid @ModelAttribute("calificacion") Calificacion calificacion, 
            BindingResult result, RedirectAttributes redir) {
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
        String id_programa = null;
        String nombre = null;
        String token = session.getAttribute("token").toString();
        if(session.getAttribute("id_programa") != null) {
            id_programa = session.getAttribute("id_programa").toString();
        }
        if(session.getAttribute("nomb_programa") != null) {
            nombre = session.getAttribute("nomb_programa").toString();
        }
        Integer id_alumno = null;
        if(session.getAttribute("id_alumno") != null) {
            id_alumno = Integer.parseInt(session.getAttribute("id_alumno").toString());
        }
        calificacion.setId_alumno(id_alumno);
        
        boolean success = calificacionService.saveCalificacion(token, calificacion);
        if (!success) {
            redir.addFlashAttribute("errorMsg", "Ha ocurrido un error al ingresar la calificación.");
            return "redirect:/administracion/programas/alumnos.htm?id="+id_programa+"&nombre="+nombre;
        }
        
        redir.addFlashAttribute("msg", "Calificación ingresada exitosamente.");
        return "redirect:/administracion/programas/alumnos.htm?id="+id_programa+"&nombre="+nombre;
    }
}
