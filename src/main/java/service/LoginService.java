package service;

import org.springframework.stereotype.Service;
import util.*;
import entities.AuthUser;
import entities.Usuario;
import org.json.JSONObject;


@Service
public class LoginService {
    
    private Requests req;
    private JWT jwt;
    
    public AuthUser getUserFromToken(String token) {
       jwt = new JWT();
       AuthUser aU = null;
       aU = jwt.decodeJWT(token);
       
       return aU;
    }
    
    public String getAuthToken(Usuario usuario) {
        req = new Requests();
        jwt = new JWT();
        
        JSONObject jObj = new JSONObject();
        jObj.accumulate("USUARIO", usuario.getUsuario());
        jObj.accumulate("CONTRASENA", usuario.getContrasena());
        String token = req.auth(jObj);
        
        return token;
    }
}
