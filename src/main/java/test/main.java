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
        
        ArrayList<Persona> lstDireccion = r.requestController("POST", "private/persona/9999", "persona", null, Persona.class, token);
        for(Persona d : lstDireccion) {
            System.out.println(d.toString());
        }
        
    }
}
