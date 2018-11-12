package controller;

import entities.AuthUser;
import entities.Ciudad;
import entities.Pais;
import entities.Usuario;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.DireccionService;
import service.UsuarioService;

@Controller
public class UsuarioController {
    
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private DireccionService direccionService;
    
    @InitBinder     
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));   
    }
    
    @RequestMapping(value = "/administracion/usuarios.htm", method = RequestMethod.GET)
    public String getUsuarios (HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "redirect:/login.htm";
        } else {
            AuthUser aU = (AuthUser)session.getAttribute("loggedUser");
            if (aU == null) {
                return "redirect:/login.htm";
            } else if (!aU.getRol().equals("Administrador")) {
                return "redirect:/home.htm";
            }
        }
        model.addAttribute("title", "Usuarios");
        String token = session.getAttribute("token").toString();
        
        ArrayList<Usuario> lstUsuario = usuarioService.getUsuarios(token);
        
        model.addAttribute("lstUsuario", lstUsuario);
        
        return "administracion/usuarios";
    }
    
    @RequestMapping(value = "/administracion/usuarios.htm", params = { "id" }, method = RequestMethod.GET)
    public String getUsuario (@RequestParam String id, HttpServletRequest request, Model model, RedirectAttributes redir) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "redirect:/login.htm";
        } else {
            AuthUser aU = (AuthUser)session.getAttribute("loggedUser");
            if (aU == null) {
                return "redirect:/login.htm";
            } else if (!aU.getRol().equals("Administrador")) {
                return "redirect:/home.htm";
            }
        }
        model.addAttribute("title", "Usuario");
        String token = session.getAttribute("token").toString();
        
        Usuario usr = usuarioService.getUsuario(token, id);
        if (usr == null) {
            redir.addFlashAttribute("errorMsg", "Ha ocurrido un error al mostrar el usuario.");
            return "redirect:/administracion/usuarios.htm";
        }
        
        ArrayList<Ciudad> lstCiudad = direccionService.getCiudades(token);
        ArrayList<Pais> lstPais = direccionService.getPaises(token);
        
        session.setAttribute("usuario", usr);
        model.addAttribute("usuario", usr);
        model.addAttribute("lstCiudad", lstCiudad);
        model.addAttribute("lstPais", lstPais);
        
        return "administracion/usuarios/ver";
    }
    
    @RequestMapping(value="/administracion/usuarios.htm", method = RequestMethod.POST)
    public String updateUsuario (HttpServletRequest request, Model model, @Valid @ModelAttribute("usuario") Usuario usuario, 
            BindingResult result, RedirectAttributes redir) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "redirect:/login.htm";
        } else {
            AuthUser aU = (AuthUser)session.getAttribute("loggedUser");
            if (aU == null) {
                return "redirect:/login.htm";
            } else if (!aU.getRol().equals("Administrador")) {
                return "redirect:/home.htm";
            }
        }
        String token = session.getAttribute("token").toString();
        Usuario u = (Usuario)session.getAttribute("usuario");
        
        if (!u.getId_usuario().equals(usuario.getId_usuario()) || !u.getPersona().getId_persona().equals(usuario.getPersona().getId_persona())
                || !u.getPersona().getDireccion().getId_direccion().equals(usuario.getPersona().getDireccion().getId_direccion())){
            redir.addFlashAttribute("errorMsg", "Error, datos incorrectos.");
            return "redirect:/administracion/usuarios.htm";
        }
        
        boolean success = usuarioService.updateUsuario(token, usuario);
        if (!success) {
            redir.addFlashAttribute("errorMsg", "Error al guardar los datos.");
            return "redirect:/administracion/usuarios.htm";
        }
        
        redir.addFlashAttribute("msg", "Todos los cambios guardados.");
        return "redirect:/administracion/usuarios.htm";
    }
    
    @RequestMapping(value = "/administracion/usuarios.htm", params = { "id", "delete" }, method = RequestMethod.GET)
    public String deleteUsuario (@RequestParam String id, HttpServletRequest request, Model model, RedirectAttributes redir) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "redirect:/login.htm";
        } else {
            AuthUser aU = (AuthUser)session.getAttribute("loggedUser");
            if (aU == null) {
                return "redirect:/login.htm";
            } else if (!aU.getRol().equals("Administrador")) {
                return "redirect:/home.htm";
            }
        }
        String token = session.getAttribute("token").toString();
        
        boolean success = usuarioService.deleteUsuario(token, id);
        
        if (!success) {
            redir.addFlashAttribute("errorMsg", "Error al eliminar el usuario.");
            return "redirect:/administracion/usuarios.htm";
        }
        
        redir.addFlashAttribute("msg", "Usuario eliminado exitosamente.");
        return "redirect:/administracion/usuarios.htm";
    }
}
