package service;

import entities.Contacto;
import entities.Direccion;
import entities.Persona;
import entities.Usuario;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import util.Requests;

@Service
public class PersonaService {
    
    private Requests req;
    
    public Persona getPersona(String token, String id_usuario){
        req = new Requests();
        DireccionService ds = new DireccionService();
        
        ArrayList<Persona> persona = req.requestController("GET", "private/persona?id_usuario=" + id_usuario, "persona", null, Persona.class, token);
        
        Persona p = null;
        if (persona == null || persona.isEmpty()){
            return new Persona();
        }
        
        p = persona.get(0);
        
        String id_direccion = p.getId_direccion().toString();
        Direccion d = ds.getDireccion(token, id_direccion);
        if (d != null) {
            p.setDireccion(d);
        }
        String id_persona = p.getId_persona().toString();
        ArrayList<Contacto> lstContactos = req.requestController("GET", "private/contacto?id_persona=" + id_persona, "contacto", null, Contacto.class, token);
        if (lstContactos != null && !lstContactos.isEmpty()) {
            p.setContacto(lstContactos.get(0));
        }
        
        return p;
    }
    
    public ArrayList<Persona> getPersonas(String token){
        req = new Requests();
        
        ArrayList<Persona> lstPersona = req.requestController("GET", "private/persona", "persona", null, Persona.class, token);
        if (lstPersona == null || lstPersona.isEmpty()){
            return null;
        }
        
        return lstPersona;
    }
    
    public ArrayList<Persona> getPersonasContactos(String token){
        req = new Requests();
        DireccionService ds = new DireccionService();
        
        ArrayList<Persona> lstPersona = req.requestController("GET", "private/persona", "persona", null, Persona.class, token);
        if (lstPersona != null && !lstPersona.isEmpty()){
            ArrayList arr = ds.getDirecciones(token);
            
            if (!arr.isEmpty() && arr.size() == 3) {
                ArrayList<Direccion> lstDireccion = (ArrayList<Direccion>)arr.get(0);
                
                int index = 0;
                ArrayList<Contacto> lstContactos = req.requestController("GET", "private/contacto", "contacto", null, Contacto.class, token);
                for (Persona p : lstPersona) {
                    // Se asignan los contactos
                    // Por ahora, solo 1 contacto por persona
                    if (lstContactos != null && !lstContactos.isEmpty()) {
                        for(Contacto c : lstContactos) {
                            if (c.getId_persona().equals(p.getId_persona())) {
                                p.setContacto(c);
                                break;
                            }
                        }
                    }
                    
                    for(Direccion d : lstDireccion) {
                        if (d.getId_direccion().equals(p.getId_direccion())){
                            p.setDireccion(d);
                            lstPersona.set(index, p);
                            break;
                        }
                    }
                    index++;
                }
                
            }
            
            return lstPersona;
        }
        
        return new ArrayList();
    }
    
    public Persona savePersona(Usuario usr, String token) {
        req = new Requests();
        DireccionService ds = new DireccionService();
        
        Direccion d = ds.saveDireccion(usr.getPersona().getDireccion(), token);
        if (d == null) {
            return null;
        }
        
        JSONObject obj = new JSONObject();
        obj.accumulate("ID_USUARIO", usr.getId_usuario());
        obj.accumulate("ID_DIRECCION", d.getId_direccion());
        obj.accumulate("RUT", usr.getPersona().getRut());
        obj.accumulate("NOMBRE", usr.getPersona().getNombre());
        obj.accumulate("APP_PATERNO", usr.getPersona().getApp_paterno());
        obj.accumulate("APP_MATERNO", usr.getPersona().getApp_materno());
        
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String fech_nacimiento = format.format(usr.getPersona().getFech_nacimiento());
        obj.accumulate("FECH_NACIMIENTO", fech_nacimiento);
        
        ArrayList<Persona> lstPersona = req.requestController("POST", "private/persona", "persona", obj, Persona.class, token);
        
        if (lstPersona == null || lstPersona.isEmpty()) {
            return null;
        }
        
        return lstPersona.get(0);
    }
    
    public Persona savePersona(Usuario usr) {
        req = new Requests();
        DireccionService ds = new DireccionService();
        
        Direccion d = ds.saveDireccion(usr.getPersona().getDireccion());
        if (d == null) {
            return null;
        }
        
        JSONObject obj = new JSONObject();
        obj.accumulate("ID_USUARIO", usr.getId_usuario());
        obj.accumulate("ID_DIRECCION", d.getId_direccion());
        obj.accumulate("RUT", usr.getPersona().getRut());
        obj.accumulate("NOMBRE", usr.getPersona().getNombre());
        obj.accumulate("APP_PATERNO", usr.getPersona().getApp_paterno());
        obj.accumulate("APP_MATERNO", usr.getPersona().getApp_materno());
        
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String fech_nacimiento = format.format(usr.getPersona().getFech_nacimiento());
        obj.accumulate("FECH_NACIMIENTO", fech_nacimiento);
        
        ArrayList<Persona> lstPersona = req.requestController("POST", "persona", "persona", obj, Persona.class, "");
        
        if (lstPersona == null || lstPersona.isEmpty()) {
            return null;
        }
        
        return lstPersona.get(0);
    }
    
    public Persona updatePersona(String token, Persona per){
        req = new Requests();
        
        
        JSONObject jObj = new JSONObject();
        jObj.accumulate("NOMBRE", per.getNombre());
        jObj.accumulate("APP_PATERNO", per.getApp_paterno());
        jObj.accumulate("APP_MATERNO", per.getApp_materno());
        jObj.accumulate("RUT", per.getRut());
        
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String fech_nacimiento = format.format(per.getFech_nacimiento());
        jObj.accumulate("FECH_NACIMIENTO", fech_nacimiento);
        jObj.accumulate("ID_DIRECCION", per.getDireccion().getId_direccion());
        
        ArrayList<Persona> lstPersona = req.requestController("PUT", "private/persona/" + per.getId_persona().toString(), "persona", jObj, Persona.class, token);
        if (lstPersona == null || lstPersona.isEmpty()) {
            return null;
        }
        
        return lstPersona.get(0);
    }
    
    /**
     * Metodo para comprobar la existencia de un RUT
     * @param rut - Rut de la persona
     * @param token - token de autenticacion
     * @return True en caso de que el rut exista, de lo contrario False.
     */
    public boolean rutExists(String rut, String token){
        req = new Requests();
        
        ArrayList<Persona> personas = req.requestController("GET", "private/persona?rut=" + rut, "persona", null, Persona.class, token);
        
        return personas != null && !personas.isEmpty();
    }
    
    public boolean rutExists(String rut){
        req = new Requests();
        
        ArrayList<Persona> personas = req.requestController("GET", "persona?rut=" + rut, "persona", null, Persona.class, "");
        
        return personas != null && !personas.isEmpty();
    }
}
