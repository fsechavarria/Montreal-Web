package service;

import entities.Direccion;
import entities.Familia;
import entities.Persona;
import entities.Rol;
import entities.Usuario;
import java.util.ArrayList;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import util.Requests;

@Service
public class FamiliaService {
    
    private Requests req;
    
    public ArrayList<Familia> getFamilias(String token) {
        req = new Requests();
        
        ArrayList<Familia> familias = req.requestController("GET", "private/familia", "familia", null, Familia.class, token);
        
        if (familias == null || familias.isEmpty()) {
            return null;
        }
        
        PersonaService ps = new PersonaService();
        
        ArrayList<Persona> personas = ps.getPersonas(token);
        if (personas != null && !personas.isEmpty()) {
            int index = 0;
            for (Familia f : familias) {
                for(Persona p : personas) {
                    if (f.getId_usuario().equals(p.getId_usuario())) {
                        f.setPersona(p);
                        familias.set(index, f);
                        break;
                    }
                }
                index++;
            }
        }
        
        return familias;
    }
    
    public Familia getFamilia(String token, String id){
        if (id == null || id.trim().length() == 0) {
            return null;
        }
        
        req = new Requests();
        
        ArrayList<Familia> lstFamilia = req.requestController("GET", "private/familia/" + id, "familia", null, Familia.class, token);
        
        if (lstFamilia == null || lstFamilia.isEmpty()){
            return null;
        }
        Familia f = lstFamilia.get(0);
        
        return f;
    }
    
    public String saveFamilia(Familia fa){
        UsuarioService us = new UsuarioService();
        ContactoService cs = new ContactoService();
        PersonaService ps = new PersonaService();
        req = new Requests();
        
        if (us.usuarioExists(fa.getUsuario().getUsuario())) {
            return "El usuario ingresado ya está registrado.";
        }
        
        if (ps.rutExists(fa.getUsuario().getPersona().getRut())) {
            return "El rut ingresado ya está registrado.";
        }
        
        ArrayList<Rol> lstRol = req.requestController("GET", "rol", "rol", null, Rol.class, "");
        Integer id_rol = 0;
        if (lstRol != null && !lstRol.isEmpty()) {
            for(Rol r : lstRol) {
                if (r.getDesc_rol().equals("Familia")) {
                    id_rol = r.getId_rol();
                    break;
                }
            }
        }
        
        fa.getUsuario().setId_rol(id_rol);
        Usuario u = us.saveUsuario(fa.getUsuario());
        
        if (u != null) {
            JSONObject obj = new JSONObject();
            obj.accumulate("ID_USUARIO", u.getId_usuario());
            obj.accumulate("NUM_INTEGRANTES", fa.getNum_integrantes());
            ArrayList<Familia> familia = req.requestController("POST", "familia", "familia", obj, Familia.class, "");
            
            if (familia == null || familia.isEmpty()) {
                // Se elimina todo en caso de error, para evitar datos duplicados y problemas en intentos subsiguientes de registro.
                us.deleteUsuario(u.getId_usuario().toString());
                req.requestController("DELETE", "direccion/" + u.getPersona().getId_persona().toString(), "direccion", null, Direccion.class, "");
                cs.deleteContactos(u.getPersona().getId_persona().toString());
                return "Error al registrar Alumno.";
            }
            return null;
        }
        
        return "Error al registrar usuario.";
    }
    
}
