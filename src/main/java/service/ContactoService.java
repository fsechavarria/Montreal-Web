package service;

import entities.Contacto;
import entities.Persona;
import java.util.ArrayList;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import util.Requests;

@Service
public class ContactoService {
    
    private Requests req;
    
    public void deleteContactos(String id_contacto, String token) {
        req = new Requests();
        
        ArrayList<Contacto> contactos = req.requestController("DELETE", "private/contacto/" + id_contacto, "contacto", null, Contacto.class, token);
    }
    
    public Contacto saveContacto(Persona per, String token) {
        req = new Requests();
        
        JSONObject obj = new JSONObject();
        obj.accumulate("ID_PERSONA", per.getId_persona());
        obj.accumulate("TIPO_CONTACTO", "Correo");
        obj.accumulate("DESC_CONTACTO", per.getContacto().getDesc_contacto());
        
        ArrayList<Contacto> contacto = req.requestController("POST", "private/contacto", "contacto", obj, Contacto.class, token);
        
        if (contacto == null || contacto.isEmpty()) {
            return null;
        }
        
        return contacto.get(0);
    }
    
    public Contacto updateContacto(String token, Contacto con) {
        req = new Requests();
        
        JSONObject obj = new JSONObject();
        obj.accumulate("DESC_CONTACTO", con.getDesc_contacto());
        
        ArrayList<Contacto> contacto = req.requestController("PUT", "private/contacto/" + con.getId_contacto().toString(), "contacto", obj, Contacto.class, token);
        if (contacto == null || contacto.isEmpty()) {
            return null;
        }
        
        return contacto.get(0);
    }
}
