package service;

import entities.CEL;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import util.Requests;

@Service
public class CelService {
    
    private Requests req;
    
    public ArrayList<CEL> getCEL(String token){
        req = new Requests();
        
        ArrayList<CEL> lstCel = req.requestController("GET", "private/cel", "cel", null, CEL.class, token);
        
        return lstCel;
    }
    
}
