package service;

import entities.Programa_Estudio;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import util.Requests;

@Service
public class ProgramaService {
    
    private Requests req;
    
    public ArrayList getProgramas(String token) {
        req = new Requests();
        
        ArrayList<Programa_Estudio> lstProgramas;
        lstProgramas = req.requestController("GET", "private/programa", "programa", null, Programa_Estudio.class, token);
        
        return lstProgramas;
    }
    
    public Programa_Estudio getPrograma(String token, String id) {
        req = new Requests();
        
        ArrayList<Programa_Estudio> lstProgramas;
        lstProgramas = req.requestController("GET", "private/programa/" + id, "programa", null, Programa_Estudio.class, token);
        
        if (lstProgramas.size() >= 1) {
            return lstProgramas.get(0);
        }
        return null;
    }
}
