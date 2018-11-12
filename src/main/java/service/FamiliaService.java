package service;

import entities.Familia;
import entities.Persona;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import util.Requests;

@Service
public class FamiliaService {
    
    private Requests req;
    
    public ArrayList<Familia> getFamilias(String token) {
        req = new Requests();
        
        ArrayList<Familia> familias = req.requestController("GET", "private/familia", "familia", null, Familia.class, token);
        
        if (familias == null || familias.isEmpty()) {
            return null;
        }
        
        PersonaService ps = new PersonaService();
        
        ArrayList<Persona> personas = ps.getPersonas(token);
        if (personas != null && !personas.isEmpty()) {
            int index = 0;
            for (Familia f : familias) {
                for(Persona p : personas) {
                    if (f.getId_usuario().equals(p.getId_usuario())) {
                        f.setPersona(p);
                        familias.set(index, f);
                        break;
                    }
                }
                index++;
            }
        }
        
        return familias;
    }
    
    public Familia getFamilia(String token, String id){
        if (id == null || id.trim().length() == 0) {
            return null;
        }
        
        req = new Requests();
        
        ArrayList<Familia> lstFamilia = req.requestController("GET", "private/familia/" + id, "familia", null, Familia.class, token);
        
        if (lstFamilia == null || lstFamilia.isEmpty()){
            return null;
        }
        Familia f = lstFamilia.get(0);
        
        return f;
    }
    
}
