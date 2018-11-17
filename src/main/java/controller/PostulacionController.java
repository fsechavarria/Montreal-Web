package controller;

import entities.AuthUser;
import entities.Familia;
import entities.Postulacion;
import entities.Seguro;
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
import service.FamiliaService;
import service.PostulacionService;
import service.SeguroService;

@Controller
public class PostulacionController {
    
    @Autowired
    private PostulacionService postulacionService;
    
    @Autowired
    private SeguroService seguroService;
    
    @Autowired
    private FamiliaService familiaService;
    
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
            redir.addFlashAttribute("errorMsg", "Ha ocurrido un error al responder la postulación.");
            return "redirect:/administracion/postulaciones.htm";
        }
        
        boolean success = postulacionService.answerPostulacion(token, id, acc);
        if (!success) {
            redir.addFlashAttribute("errorMsg", "Ha ocurrido un error al responder la postulación.");
            return "redirect:/administracion/postulaciones.htm";
        }
        
        redir.addFlashAttribute("msg", "Postulación " + msg + " exitosamente.");
        return "redirect:/administracion/postulaciones.htm";
    }
 
    @RequestMapping(value="/administracion/postulacion.htm", params = { "id", "postular" }, method = RequestMethod.GET)
    public String nuevaPostulacion(@RequestParam String id, HttpServletRequest request, RedirectAttributes redir, Model model) {
        HttpSession session = request.getSession(false);
        AuthUser aU = null;
        if (session == null) {
            return "redirect:/login.htm";
        } else {
            aU = (AuthUser)session.getAttribute("loggedUser");
            if (aU == null) {
                return "redirect:/login.htm";
            }
        }
        String token = session.getAttribute("token").toString();
        
        Seguro seguro = seguroService.getSeguro(token);
        if (seguro == null) {
            redir.addFlashAttribute("errorMsg", "Ha ocurrido un error al cargar el formulario.");
            return "redirect:/administracion/programas.htm";
        }
        ArrayList<Familia> lstFamilias = familiaService.getFamilias(token);
        
        model.addAttribute("seguro", seguro);
        model.addAttribute("postulacion", new Postulacion());
        model.addAttribute("lstFamilias", lstFamilias);
        session.setAttribute("id_programa", id);
        session.setAttribute("id_seguro", seguro.getId_seguro());
        return "administracion/postulacion/nuevo";
    }
    
    @RequestMapping(value="/administracion/postulacion.htm", params = { "id", "postular" }, method = RequestMethod.POST)
    public String guardarPostulacion(@RequestParam String id, HttpServletRequest request, Model model,
            @Valid @ModelAttribute("postulacion") Postulacion postulacion, BindingResult result, RedirectAttributes redir) {
        HttpSession session = request.getSession(false);
        AuthUser aU = null;
        if (session == null) {
            return "redirect:/login.htm";
        } else {
            aU = (AuthUser)session.getAttribute("loggedUser");
            if (aU == null) {
                return "redirect:/login.htm";
            }
        }
        String token = session.getAttribute("token").toString();
        String id_usuario = aU.getId().toString();
        boolean id_success = true;
        try {
            Integer id_programa = Integer.parseInt(session.getAttribute("id_programa").toString());
            Integer id_seguro = Integer.parseInt(session.getAttribute("id_seguro").toString());
            postulacion.setId_programa(id_programa);
            postulacion.setId_seguro(id_seguro);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            id_success = false;
        } finally {
            session.removeAttribute("id_programa");
            session.removeAttribute("id_seguro");
        }
        
        if (id_success) {
            boolean success = postulacionService.nuevaPostulacion(postulacion, token, id_usuario);
            if (!success) {
                redir.addFlashAttribute("errorMsg", "Ha ocurrido un error al guardar la postulación.");
                return "redirect:/administracion/programas.htm";
            }
        } else {
            redir.addFlashAttribute("errorMsg", "Ha ocurrido un error al guardar la postulación.");
            return "redirect:/administracion/programas.htm";
        }
       
        redir.addFlashAttribute("msg", "Se ha postulado correctamente.");
        return "redirect:/administracion/programas.htm";
    }
    
}
