package service;

import entities.CEM;
import entities.Direccion;
import entities.Rol;
import entities.Usuario;
import java.util.ArrayList;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import util.Requests;

@Service
public class CEMService {

    private Requests req;
    
    public String saveCEM(CEM cem, String token){
        req = new Requests();
        UsuarioService us = new UsuarioService();
        PersonaService ps = new PersonaService();
        
        if (us.usuarioExists(cem.getUsuario().getUsuario(), token)) {
            return "El usuario ingresado ya está registrado.";
        }
        
        if (ps.rutExists(cem.getUsuario().getPersona().getRut(), token)) {
            return "El rut ingresado ya está registrado.";
        }
        
        ArrayList<Rol> lstRol = req.requestController("GET", "private/rol", "rol", null, Rol.class, token);
        Integer id_rol = 0;
        if (lstRol != null && !lstRol.isEmpty()) {
            for(Rol r : lstRol) {
                if (r.getDesc_rol().equals("CEM")) {
                    id_rol = r.getId_rol();
                    break;
                }
            }
        }
        
        cem.getUsuario().setId_rol(id_rol);
        Usuario u = us.saveUsuario(cem.getUsuario(), token);
        
        if (u != null) {
            JSONObject obj = new JSONObject();
            obj.accumulate("ID_USUARIO", u.getId_usuario());
            obj.accumulate("NOM_CENTRO", cem.getNom_centro());
            
            ArrayList<CEM> lstCEM = req.requestController("POST", "private/cem", "cem", obj, CEM.class, token);
            
            if (lstCEM == null || lstCEM.isEmpty()) {
                // Se elimina todo en caso de error, para evitar datos duplicados y problemas en intentos subsiguientes de registro.
                req.requestController("DELETE", "private/usuario/" + u.getId_usuario().toString(), "usuario", null, Usuario.class, token);
                req.requestController("DELETE", "private/direccion/" + u.getPersona().getId_persona().toString(), "direccion", null, Direccion.class, token);
                us.deleteContactos(u.getPersona().getId_persona().toString(), token);
                return "Error al registrar CEM.";
            }
            return null;
        }
        
        return "Error al registrar usuario.";
    }
}
