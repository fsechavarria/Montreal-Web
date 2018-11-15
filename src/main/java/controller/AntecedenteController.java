package controller;

import entities.Antecedente;
import entities.AuthUser;
import entities.Familia;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.AntecedenteService;
import service.FamiliaService;

@Controller
public class AntecedenteController {
    
    @Autowired
    private AntecedenteService antecedenteService;
    
    @Autowired
    private FamiliaService familiaService;
    
    @RequestMapping(value = "/administracion/antecedentes.htm", method = RequestMethod.GET)
    public String getAntecedentes (HttpServletRequest request, Model model) {
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
        model.addAttribute("title", "Antecedentes");
        String token = session.getAttribute("token").toString();
        
        ArrayList<Antecedente> lstAntecedentes = antecedenteService.getAntecedentes(token);
        if (lstAntecedentes == null) {
            lstAntecedentes = new ArrayList();
        }
        
        model.addAttribute("lstAntecedentes", lstAntecedentes);
        
        return "administracion/antecedentes";
    }
    
    @RequestMapping(value = "/administracion/antecedentes.htm", params = { "id" },method = RequestMethod.GET)
    public String getAntecedente(@RequestParam String id, HttpServletRequest request, Model model, RedirectAttributes redir){
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
        model.addAttribute("title", "Antecedente");
        String token = session.getAttribute("token").toString();
        
        Antecedente a = antecedenteService.getAntecedente(id, token);
        if (a == null) {
            redir.addFlashAttribute("errorMsg", "Ha ocurrido al mostrar el antecedente.");
            return "redirect:/administracion/antecedentes.htm";
        }
        
        model.addAttribute("antecedente", a);
        session.setAttribute("antecedente", a);
        return "administracion/antecedentes/ver";
    }
    
    @RequestMapping(value="/administracion/antecedentes.htm", params = { "id" }, method = RequestMethod.POST)
    public String updateAntecedente (@RequestParam String id, HttpServletRequest request, Model model,
            @Valid @ModelAttribute("antecedente") Antecedente antecedente, BindingResult result, RedirectAttributes redir) {
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
        
        Antecedente ant = (Antecedente)session.getAttribute("antecedente");
        if (!ant.getId_antecedente().equals(antecedente.getId_antecedente())) {
            redir.addFlashAttribute("errorMsg", "Ha ocurrido al guardar el antecedente.");
            return "redirect:/administracion/antecedentes.htm";
        }
        
        boolean success = antecedenteService.updateAntecedente(antecedente, token);
        if (!success) {
            redir.addFlashAttribute("errorMsg", "Ha ocurrido al guardar el antecedente.");
            return "redirect:/administracion/antecedentes.htm";
        }
        
        redir.addFlashAttribute("msg", "Antecedente actualizado exitosamente.");
        return "redirect:/administracion/antecedentes.htm";
    }
    
    @RequestMapping(value = "/administracion/antecedentes.htm", params = { "id", "delete" },method = RequestMethod.GET)
    public String deleteAntecedente(@RequestParam String id, HttpServletRequest request, Model model, RedirectAttributes redir){
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
        
        boolean success = antecedenteService.deleteAntecedente(id, token);
        if (!success){
            redir.addFlashAttribute("errorMsg", "Ha ocurrido al eliminar el antecedente.");
            return "redirect:/administracion/antecedentes.htm";
        }
        
        redir.addFlashAttribute("msg", "Antecedente eliminado exitosamente.");
        return "redirect:/administracion/antecedentes.htm";
    }
    
    @RequestMapping(value = "/administracion/antecedentes/nuevo.htm", method = RequestMethod.GET)
    public String nuevoAntecedente(HttpServletRequest request, Model model){
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
        model.addAttribute("title", "Cargar Antecedente");
        
        if (aU.getRol().equals("Administrador")){
            ArrayList<Familia> lstFamilias = familiaService.getFamilias(token);
            
            model.addAttribute("antecedente", new Antecedente());
            model.addAttribute("lstFamilias", lstFamilias);
            return "administracion/antecedentes/nuevo";
        } else {
            model.addAttribute("antecedente", new Antecedente());
            return "administracion/antecedentes/nuevo";
        }
    }
    
    @RequestMapping(value = "/administracion/antecedentes/nuevo.htm", method = RequestMethod.POST)
    public String saveAntecedente(@RequestParam("file") MultipartFile file, HttpServletRequest request, Model model,
        RedirectAttributes redir, @Valid @ModelAttribute("antecedente") Antecedente antecedente, BindingResult result){
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
        
        boolean success = antecedenteService.saveAntecedente(antecedente, file, token);
        
        return "redirect:/administracion/antecedentes.htm";
    }
    
}
