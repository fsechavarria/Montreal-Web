package service;

import entities.Ciudad;
import entities.Direccion;
import entities.Pais;
import java.util.ArrayList;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import util.Requests;

@Service
public class DireccionService {
    
    private Requests req;
    
    // Obtiene las direcciones con sus respectivos paises y ciudad
    // y retorna las 3 listas en el orden [0] direcciones [1] ciudades [2] paises
    public ArrayList getDirecciones(String token){
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
    
    public Direccion getDireccion(String token, String id_direccion) {
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
    
    public Direccion saveDireccion(Direccion dir, String token){
        req = new Requests();
        
        JSONObject obj = new JSONObject();
        obj.accumulate("ID_CIUDAD", dir.getId_ciudad());
        obj.accumulate("CALLE", dir.getCalle());
        obj.accumulate("NUMERACION", dir.getNumeracion());
        obj.accumulate("DEPARTAMENTO", dir.getDepartamento());
        
        ArrayList<Direccion> direccion = req.requestController("POST", "private/direccion", "direccion", obj, Direccion.class, token);
        
        if (direccion == null || direccion.isEmpty()) {
            return null;
        }
        
        return direccion.get(0);
    }
    
    public Direccion updateDireccion(String token, Direccion dir){
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
}
