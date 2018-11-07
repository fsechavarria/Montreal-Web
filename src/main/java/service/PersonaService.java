package service;

import entities.Persona;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import util.Requests;

@Service
public class PersonaService {
    
    private Requests req;
    
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
}
