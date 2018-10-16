package util;

import entities.AuthUser;
import java.util.Iterator;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;

public class JWT {

    /**
     * Método para decodificar tokens JWT.
     * @param token
     * @return Retorna un objeto de tipo AuthUser instanciado con los datos provenientes del token. Nombre, ID y rol.
     */
    public AuthUser decodeJWT (String token) {
        Base64 base64Url = new Base64(true);
        String[] split_string = token.split("\\.");
        String base64EncodedBody = split_string[1];
        
        String body = new String(base64Url.decode(base64EncodedBody));
        JSONObject obj = new JSONObject(body);
        
        AuthUser u = new AuthUser();
        Iterator<String> keys = obj.keys();
        while(keys.hasNext()) {
            String key = keys.next();
            if (key.toLowerCase().equals("nombre")) u.setNombre(obj.get(key).toString());
            if (key.toLowerCase().equals("id")) u.setId(obj.getInt(key));
            if (key.toLowerCase().equals("rol")) u.setRol(obj.get(key).toString());
        }
        
        return u;
    }
}
