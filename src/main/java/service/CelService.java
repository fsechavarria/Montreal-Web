package service;

import entities.CEL;
import entities.Direccion;
import entities.Rol;
import entities.Usuario;
import java.util.ArrayList;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import util.Requests;

@Service
public class CelService {
    
    private Requests req;
    
    public ArrayList<CEL> getCEL(String token){
        req = new Requests();
        
        ArrayList<CEL> lstCel = req.requestController("GET", "private/cel", "cel", null, CEL.class, token);
        
        return lstCel;
    }
    
    public String saveCEL(CEL cel, String token){
        req = new Requests();
        UsuarioService us = new UsuarioService();
        PersonaService ps = new PersonaService();
        
        if (us.usuarioExists(cel.getUsuario().getUsuario(), token)) {
            return "El usuario ingresado ya está registrado.";
        }
        
        if (ps.rutExists(cel.getUsuario().getPersona().getRut(), token)) {
            return "El rut ingresado ya está registrado.";
        }
        
        ArrayList<Rol> lstRol = req.requestController("GET", "private/rol", "rol", null, Rol.class, token);
        Integer id_rol = 0;
        if (lstRol != null && !lstRol.isEmpty()) {
            for(Rol r : lstRol) {
                if (r.getDesc_rol().equals("CEL")) {
                    id_rol = r.getId_rol();
                    break;
                }
            }
        }
        cel.getUsuario().setId_rol(id_rol);
        Usuario u = us.saveUsuario(cel.getUsuario(), token);
        
        if (u != null) {
            JSONObject obj = new JSONObject();
            obj.accumulate("ID_USUARIO", u.getId_usuario());
            obj.accumulate("NOM_CENTRO", cel.getNom_centro());
            
            ArrayList<CEL> lstCEL = req.requestController("POST", "private/cel", "cel", obj, CEL.class, token);
            
            
            if (lstCEL == null || lstCEL.isEmpty()) {
                // Se elimina todo en caso de error, para evitar datos duplicados y problemas en intentos subsiguientes de registro.
                us.deleteUsuario(token, u.getId_usuario().toString());
                req.requestController("DELETE", "private/direccion/" + u.getPersona().getId_direccion().toString(), "direccion", null, Direccion.class, token);
                us.deleteContactos(u.getPersona().getId_persona().toString(), token);
                return "Error al registrar CEL.";
            }
            
            return null;
        }
        
        return "Error al registrar usuario.";
    }
    
}
