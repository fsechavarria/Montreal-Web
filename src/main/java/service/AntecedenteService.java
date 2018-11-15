package service;

import entities.Antecedente;
import entities.Familia;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import util.Requests;

@Service
public class AntecedenteService {
    
    private Requests req;
    
    public Antecedente getAntecedente(String id, String token) {
        if (id == null || id.trim().length() == 0) {
            return null;
        }
        req = new Requests();
        
        ArrayList<Antecedente> antecedentes = req.requestController("GET", "private/antecedente/" + id, "antecedente", null, Antecedente.class, token);
        if (antecedentes == null || antecedentes.isEmpty()) {
            return null;
        }
        
        return antecedentes.get(0);
    }
    
    public ArrayList getAntecedentes(String token){
        req = new Requests();
        
        ArrayList<Antecedente> antecedentes = req.requestController("GET", "private/antecedente", "antecedente", null, Antecedente.class, token);
        
        if (antecedentes == null || antecedentes.isEmpty()) {
            return null;
        }
        FamiliaService fs = new FamiliaService();
        
        ArrayList<Familia> familias = fs.getFamilias(token);
        if (familias != null && !familias.isEmpty()) {
            
            int index = 0;
            for (Antecedente a : antecedentes) {
                for(Familia f : familias) {
                    if (a.getId_familia().equals(f.getId_familia())) {
                        a.setFamilia(f);
                        antecedentes.set(index, a);
                        break;
                    }
                }
                index++;
            }
        }
        
        return antecedentes;
    }
 
    public ArrayList getAntecedentes(String token, String id_usuario){
        req = new Requests();
        
        ArrayList<Familia> familia = req.requestController("GET", "private/familia?id_usuario=" + id_usuario, "familia", null, Familia.class, token);
        
        if (familia != null && !familia.isEmpty()) 
        {
            String id_familia = familia.get(0).getId_familia().toString();
            ArrayList<Antecedente> antecedentes = req.requestController("GET", "private/antecedente?id_familia=" + id_familia, "antecedente", null, Antecedente.class, token);
            
            if (antecedentes != null) {
                return antecedentes;
            } else {
                return new ArrayList();
            }
        }
        return new ArrayList();
    }
    
    public boolean updateAntecedente(Antecedente ant, String token) {
        req = new Requests();
        
        JSONObject obj = new JSONObject();
        obj.accumulate("DESC_ANTECEDENTE", ant.getDesc_antecedente());
        
        String id_antecedente = ant.getId_antecedente().toString();
        
        ArrayList<Antecedente> antecedente = req.requestController("PUT", "private/antecedente/" + id_antecedente, "antecedente", obj, Antecedente.class, token);
        
        return !(antecedente == null || antecedente.isEmpty());
    }
    
    public boolean deleteAntecedente(String id_antecedente, String token) {
        if (id_antecedente == null || id_antecedente.trim().length() == 0) {
            return false;
        } 
        req = new Requests();
        
        ArrayList<Antecedente> antecedente = req.requestController("DELETE", "private/antecedente/" + id_antecedente, "antecedente", null, Antecedente.class, token);
        
        return !(antecedente == null || antecedente.isEmpty());
    }

    public boolean saveAntecedente(Antecedente antecedente, MultipartFile file, String token) {
        req = new Requests();
        
        
        JSONObject obj = new JSONObject();
        obj.accumulate("ID_FAMILIA", antecedente.getId_familia());
        obj.accumulate("DESC_ANTECEDENTE", antecedente.getDesc_antecedente());
        req.sendFile("private/antecedente", "antecedente", file, obj, token);
        
        return true;
    }
    
    public boolean saveAntecedente(Antecedente antecedente, MultipartFile file, String token, String id_usuario) {
        req = new Requests();
        
        ArrayList<Familia> familia = req.requestController("GET", "private/familia?id_usuario=" + id_usuario, "familia", null, Familia.class, token);
        if (familia != null && !familia.isEmpty()) {
            String id_familia = familia.get(0).getId_familia().toString();
            
            JSONObject obj = new JSONObject();
            obj.accumulate("ID_FAMILIA", id_familia);
            obj.accumulate("DESC_ANTECEDENTE", antecedente.getDesc_antecedente());
            req.sendFile("private/antecedente", "antecedente", file, obj, token);
            
            return true;
        }
        
        return false;
    }
    
    
}
