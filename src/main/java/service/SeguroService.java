package service;

import entities.Seguro;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import util.Requests;

@Service
public class SeguroService {
    
    private Requests req;
    
    public Seguro getSeguro(String token, String id){
        if (id == null || id.trim().length() == 0) {
            return null;
        }
        
        req = new Requests();
        
        ArrayList<Seguro> lstSeguro = req.requestController("GET", "private/seguro/" + id, "seguro", null, Seguro.class, token);
        
        if (lstSeguro == null || lstSeguro.isEmpty()){
            return null;
        }
        
        return lstSeguro.get(0);
    }
}
