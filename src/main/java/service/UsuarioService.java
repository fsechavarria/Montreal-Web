package service;

import entities.Contacto;
import entities.Direccion;
import entities.Persona;
import entities.Rol;
import entities.Usuario;
import java.util.ArrayList;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import util.Requests;

@Service
public class UsuarioService {
    
    private Requests req;
    
    public ArrayList<Usuario> getUsuarios(String token) {
        req = new Requests();
        PersonaService ps = new PersonaService();
        
        ArrayList<Usuario> lstUsuario = req.requestController("GET", "private/usuario", "usuario", null, Usuario.class, token);
        
        if (lstUsuario == null || lstUsuario.isEmpty()) {
            return new ArrayList();
        }
        
        ArrayList<Persona> lstPersona = ps.getPersonasContactos(token);
        if (!lstPersona.isEmpty()) {
            int index = 0;
            for(Usuario u : lstUsuario) {
                for(Persona p : lstPersona) {
                    if (p.getId_usuario().equals(u.getId_usuario())){
                        u.setPersona(p);
                        lstUsuario.set(index, u);
                        break;
                    }
                }
                index++;
            }
        }
        
        ArrayList<Rol> lstRol = req.requestController("GET", "private/rol", "rol", null, Rol.class, token);
        if (lstRol != null && !lstRol.isEmpty()){
            int index = 0;
            for (Usuario u : lstUsuario) {
                for (Rol r : lstRol){
                    if (r.getId_rol().equals(u.getId_rol())){
                        u.setRol(r);
                        lstUsuario.set(index, u);
                        break;
                    }
                }
                index++;
            }
        }
        
        return lstUsuario;
    }
    
    
    public Usuario getUsuario(String token, String id) {
        if (id == null || id.trim().length() == 0) {
            return null;
        }
        
        req = new Requests();
        PersonaService ps = new PersonaService();
        
        ArrayList<Usuario> lstUsuario = req.requestController("GET", "private/usuario/" + id, "usuario", null, Usuario.class, token);
        
        Usuario u = null;
        if (lstUsuario == null || lstUsuario.isEmpty()) {
            return new Usuario();
        }
        
        u = lstUsuario.get(0);
        Persona p = ps.getPersona(token, id);
        if (p != null) {
            u.setPersona(p);
        }
        
        return u;
    }
    
    public Usuario saveUsuario(Usuario usr, String token) {
        req = new Requests();
        ContactoService cs = new ContactoService();
        PersonaService ps = new PersonaService();
        
        JSONObject obj = new JSONObject();
        obj.accumulate("ID_ROL", usr.getId_rol());
        obj.accumulate("USUARIO", usr.getUsuario());
        obj.accumulate("CONTRASENA", usr.getContrasena());
        
        ArrayList<Usuario> lstUsuario = req.requestController("POST", "private/usuario", "usuario", obj, Usuario.class, token);
        
        if (lstUsuario == null || lstUsuario.isEmpty()) {
            return null;
        }
        
        Integer id_usuario = lstUsuario.get(0).getId_usuario();
        
        usr.setId_usuario(id_usuario);
        Persona p = ps.savePersona(usr, token);
        if (p == null) {
            this.deleteUsuario(token, id_usuario.toString());
            return null;
        } else {
            usr.getPersona().setId_persona(p.getId_persona());
            usr.getPersona().setId_direccion(p.getId_direccion());
            Contacto c = cs.saveContacto(usr.getPersona(), token);
            if (c == null) {
                this.deleteUsuario(token, id_usuario.toString());
                return null;
            }
            usr.getPersona().getContacto().setId_contacto(c.getId_contacto());
        }
        
        return usr;
    }
    
    
    public Usuario saveUsuario(Usuario usr) {
        req = new Requests();
        ContactoService cs = new ContactoService();
        PersonaService ps = new PersonaService();
        
        JSONObject obj = new JSONObject();
        obj.accumulate("ID_ROL", usr.getId_rol());
        obj.accumulate("USUARIO", usr.getUsuario());
        obj.accumulate("CONTRASENA", usr.getContrasena());
        
        ArrayList<Usuario> lstUsuario = req.requestController("POST", "usuario", "usuario", obj, Usuario.class, "");
        
        if (lstUsuario == null || lstUsuario.isEmpty()) {
            return null;
        }
        
        Integer id_usuario = lstUsuario.get(0).getId_usuario();
        
        usr.setId_usuario(id_usuario);
        Persona p = ps.savePersona(usr);
        if (p == null) {
            this.deleteUsuario(id_usuario.toString());
            return null;
        } else {
            usr.getPersona().setId_persona(p.getId_persona());
            usr.getPersona().setId_direccion(p.getId_direccion());
            Contacto c = cs.saveContacto(usr.getPersona());
            if (c == null) {
                this.deleteUsuario(id_usuario.toString());
                return null;
            }
            usr.getPersona().getContacto().setId_contacto(c.getId_contacto());
        }
        
        return usr;
    }
    
    public boolean usuarioExists(String usuario, String token){
        req = new Requests();
        
        ArrayList<Usuario> usuarios = req.requestController("GET", "private/usuario?usuario=" + usuario, "usuario", null, Usuario.class, token);
        
        return usuarios != null && !usuarios.isEmpty();
    }
    
    public boolean usuarioExists(String usuario){
        req = new Requests();
        
        ArrayList<Usuario> usuarios = req.requestController("GET", "usuario?usuario=" + usuario, "usuario", null, Usuario.class, "");
        
        return usuarios != null && !usuarios.isEmpty();
    }
    
    public boolean updateUsuario(String token, Usuario usr) {
        req = new Requests();
        ContactoService cs = new ContactoService();
        PersonaService ps = new PersonaService();
        DireccionService ds = new DireccionService();
        
        String id = usr.getId_usuario().toString();
        boolean success = false;
        
        
        JSONObject jObj = new JSONObject();
        if (usr.getContrasena() != null && usr.getContrasena().trim().length() > 0) {
            jObj.accumulate("CONTRASENA", usr.getContrasena());
        }
        
        if (usr.getUsuario() != null && usr.getUsuario().trim().length() > 0) {
            jObj.accumulate("USUARIO", usr.getUsuario());
        }
        ArrayList<Usuario> lstUsuario = req.requestController("PUT", "private/usuario/" + id, "usuario", jObj, Usuario.class, token);
        
        if (lstUsuario != null && !lstUsuario.isEmpty()) {
            if (usr.getPersona() != null) {
                Direccion dir = ds.updateDireccion(token, usr.getPersona().getDireccion());
                if (dir != null) {
                    Persona per = ps.updatePersona(token, usr.getPersona());
                    if (per != null) {
                        Contacto cont = cs.updateContacto(token, usr.getPersona().getContacto());
                        if (cont != null) success = true;
                    }
                }
            }
        }
        
        return success;
    }
    
    public boolean deleteUsuario(String token, String id) {
        if (id == null || id.trim().length() == 0) {
            return false;
        }
        
        req = new Requests();
        
        ArrayList<Usuario> lstUsuario = req.requestController("DELETE", "private/usuario/" + id, "usuario", null, Usuario.class, token);
        
        if (lstUsuario == null || lstUsuario.isEmpty()) {
            return false;
        }
        
        return true;
    }
    
    public boolean deleteUsuario(String id) {
        if (id == null || id.trim().length() == 0) {
            return false;
        }
        
        req = new Requests();
        
        ArrayList<Usuario> lstUsuario = req.requestController("DELETE", "usuario/" + id, "usuario", null, Usuario.class, "");
        
        if (lstUsuario == null || lstUsuario.isEmpty()) {
            return false;
        }
        
        return true;
    }

}
