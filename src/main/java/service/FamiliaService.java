package service;

import entities.Antecedente;
import entities.Familia;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import util.Requests;

@Service
public class FamiliaService {
    
    private Requests req;
    
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
        /*
        String id_familia = f.getId_familia().toString();
        
        ArrayList<Antecedente> lstAntecedente = req.requestController("GET", "private/antecedente?id_familia" + id_familia, "antecedente", null, Antecedente.class, token);
        
        if (lstAntecedente != null && !lstAntecedente.isEmpty()){
            
        }
        */
        return f;
    }
}
