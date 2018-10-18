package test;

import entities.*;
import java.util.ArrayList;
import org.json.JSONObject;
import util.*;

public class main {
    
    public static void main (String[] args) {
        Requests r = new Requests();
        JWT j = new JWT();
        
        JSONObject auth = new JSONObject();
        auth.accumulate("USUARIO", "admin");
        auth.accumulate("CONTRASENA", "admin");
        String token = r.auth(auth);
        ArrayList<Programa_Estudio> lstDireccion = r.requestController("GET", "programa", "programa", null, Programa_Estudio.class, token);
        for(Programa_Estudio d : lstDireccion) {
            System.out.println(d.toString());
        }
        
    }
}
