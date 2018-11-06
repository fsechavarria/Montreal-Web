package service;

import entities.CEL;
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
    
    public boolean saveCEL(CEL cel, String token){
        req = new Requests();
        UsuarioService us = new UsuarioService();
        
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
                return false;
            }
            
            return true;
        }
        
        return false;
    }
    
}
