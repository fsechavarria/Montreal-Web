package service;

import entities.Ciudad;
import entities.Direccion;
import entities.Pais;
import entities.Persona;
import entities.Rol;
import entities.Usuario;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import util.Requests;

@Service
public class UsuarioService {
    
    private Requests req;
    
    public ArrayList<Usuario> getUsuarios(String token) {
        req = new Requests();
        
        ArrayList<Usuario> lstUsuario = req.requestController("GET", "private/usuario", "usuario", null, Usuario.class, token);
        
        if (lstUsuario == null || lstUsuario.isEmpty()) {
            return new ArrayList();
        }
        
        ArrayList<Persona> lstPersona = this.getPersonas(token);
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
        
        ArrayList<Usuario> lstUsuario = req.requestController("GET", "private/usuario/" + id, "usuario", null, Usuario.class, token);
        
        Usuario u = null;
        if (lstUsuario == null || lstUsuario.isEmpty()) {
            return u;
        }
        u = lstUsuario.get(0);
        Persona p = this.getPersona(token, id);
        if (p != null) {
            u.setPersona(p);
        }
        
        return u;
    }
    
    public ArrayList<Pais> getPaises(String token) {
        req = new Requests();
        
        ArrayList<Pais> lstPais = req.requestController("GET", "private/pais", "pais", null, Pais.class, token);
        
        if (lstPais == null || lstPais.isEmpty()){
            return new ArrayList();
        }
        
        return lstPais;
    }
    
    public ArrayList<Ciudad> getCiudades(String token){
        req = new Requests();
        
        ArrayList<Ciudad> lstCiudad = req.requestController("GET", "private/ciudad", "ciudad", null, Ciudad.class, token);
        
        if (lstCiudad == null || lstCiudad.isEmpty()){
            return new ArrayList();
        }
        
        return lstCiudad;
    }
    
    public boolean updateUsuario(String token, Usuario usr) {
        String id = usr.getId_usuario().toString();
        boolean success = false;
        req = new Requests();
        
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
                Direccion dir = this.updateDireccion(token, usr.getPersona().getDireccion());
                if (dir != null) {
                    Persona per = this.updatePersona(token, usr.getPersona());
                    if (per != null) {
                        success = true;
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
    
    private Persona updatePersona(String token, Persona per){
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
    
    private Direccion updateDireccion(String token, Direccion dir){
        req = new Requests();
        
        JSONObject obj = new JSONObject();
        obj.accumulate("CALLE", dir.getCalle());
        obj.accumulate("NUMERACION", dir.getNumeracion());
        obj.accumulate("DEPARTAMENTO", dir.getDepartamento());
        obj.accumulate("ID_CIUDAD", dir.getId_ciudad());

        ArrayList<Direccion> lstDireccion = req.requestController("PUT", "private/direccion/" + dir.getId_direccion().toString(), "direccion", obj, Direccion.class, token);

        if (lstDireccion != null && !lstDireccion.isEmpty()){
            return lstDireccion.get(0);
        }
        
        
        return null;
    }
    
    private Persona getPersona(String token, String id_usuario){
        req = new Requests();
        
        ArrayList<Persona> persona = req.requestController("GET", "private/persona?id_usuario=" + id_usuario, "persona", null, Persona.class, token);
        
        Persona p = null;
        if (persona == null || persona.isEmpty()){
            return p;
        }
        p = persona.get(0);
        String id_direccion = p.getId_direccion().toString();
        
        Direccion d = this.getDireccion(token, id_direccion);
        if (d != null) {
            p.setDireccion(d);
        }
        
        return p;
    }
    
    private Direccion getDireccion(String token, String id_direccion) {
        req = new Requests();
        Direccion d = null;
        ArrayList<Direccion> lstDireccion = req.requestController("GET", "private/direccion/" + id_direccion, "direccion", null, Direccion.class, token);
       
        if (lstDireccion == null || lstDireccion.isEmpty()) {
            return d;
        }
        d = lstDireccion.get(0);
        String id_ciudad = d.getId_ciudad().toString();
        
        ArrayList<Ciudad> lstCiudad = req.requestController("GET", "private/ciudad/" + id_ciudad, "ciudad", null, Ciudad.class, token);
        if (lstCiudad != null && !lstCiudad.isEmpty()){
            Ciudad c = lstCiudad.get(0);
            d.setCiudad(c);
            String id_pais = c.getId_pais().toString();
            
            ArrayList<Pais> lstPais = req.requestController("GET", "private/pais/" + id_pais, "pais", null, Pais.class, token);
            
            if (lstPais != null && !lstPais.isEmpty()) {
                Pais p = lstPais.get(0);
                d.getCiudad().setPais(p);
            }
        }
        
        return d;
    }
    
    private ArrayList<Persona> getPersonas(String token){
        req = new Requests();
        
        ArrayList<Persona> lstPersona = req.requestController("GET", "private/persona", "persona", null, Persona.class, token);
        if (lstPersona != null && !lstPersona.isEmpty()){
            ArrayList arr = this.getDirecciones(token);
            
            if (!arr.isEmpty() && arr.size() == 3) {
                ArrayList<Direccion> lstDireccion = (ArrayList<Direccion>)arr.get(0);
                ArrayList<Ciudad> lstCiudad = (ArrayList<Ciudad>)arr.get(1);
                ArrayList<Pais> lstPais = (ArrayList<Pais>)arr.get(2);
                
                int index = 0;
                for (Persona p : lstPersona) {
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
    
    // Obtiene las direcciones con sus respectivos paises y ciudad
    // y retorna las 3 listas en el orden [0] direcciones [1] ciudades [2] paises
    private ArrayList getDirecciones(String token){
        req = new Requests();
        
        ArrayList<Direccion> lstDireccion = req.requestController("GET", "private/direccion", "direccion", null, Direccion.class, token);
        ArrayList<Ciudad> lstCiudad = req.requestController("GET", "private/ciudad", "ciudad", null, Ciudad.class, token);
        ArrayList<Pais> lstPais = req.requestController("GET", "private/pais", "pais", null, Pais.class, token);

        if (lstDireccion == null || lstDireccion.isEmpty()) {
            return new ArrayList();
        }
        
        int index = 0;
        // Asignar los paises a cada ciudad.
        for (Ciudad c : lstCiudad) {
            for(Pais p : lstPais) {
                if (p.getId_pais().equals(c.getId_pais())) {
                    c.setPais(p);
                    lstCiudad.set(index, c);
                    break;
                }
            }
            index++;
        }
            
        // Asignar las ciudades(con sus paises correspondientes) a cada direccion
        index = 0;
        for (Direccion d : lstDireccion) {
            for(Ciudad c : lstCiudad) {
                if (c.getId_ciudad().equals(d.getId_ciudad())) {
                    d.setCiudad(c);
                    lstDireccion.set(index, d);
                    break;
                }
            }
            index++;
        }
        
        ArrayList arr = new ArrayList();
        arr.add(lstDireccion);
        arr.add(lstCiudad);
        arr.add(lstPais);
        
        return arr;
    }
}