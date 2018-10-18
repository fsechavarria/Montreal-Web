package test;

import entities.*;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;
import util.*;

public class main {
    
    public static void main (String[] args) {
        Requests r = new Requests();
        JWT j = new JWT();
        
        JSONObject admin = new JSONObject();
        admin.accumulate("ID_ROL", 1);
        admin.accumulate("NOMBRE", "Administrador");
        admin.accumulate("USUARIO", "admin");
        admin.accumulate("CONTRASENA", "admin");
        ArrayList<Usuario> usuario = r.requestController("POST", "usuario", "usuario", admin, Usuario.class, "");
        int id_usuario = usuario.get(0).getId_usuario(); 
        
        JSONObject personaAdmin = new JSONObject();
        personaAdmin.accumulate("ID_USUARIO", id_usuario);
        personaAdmin.accumulate("ID_DIRECCION", 1);
        personaAdmin.accumulate("RUT", "194063571");
        personaAdmin.accumulate("NOMBRE", "Felipe Ignacio");
        personaAdmin.accumulate("APP_PATERNO", "Saa");
        personaAdmin.accumulate("APP_MATERNO", "Echavarría");
        personaAdmin.accumulate("FECH_NACIMIENTO", "13/04/1996");
        
        
        JSONObject cem = new JSONObject();
        cem.accumulate("ID_ROL", 2);
        cem.accumulate("NOMBRE", "Montreal");
        cem.accumulate("USUARIO", "cem");
        cem.accumulate("CONTRASENA", "cem");
        ArrayList<CEM> cm = r.requestController("POST", "usuario", "usuario", cem, CEM.class, "");
        int id_usuarioCem = cm.get(0).getId_usuario();
        
        JSONObject personaCem = new JSONObject();
        personaCem.accumulate("ID_USUARIO", id_usuarioCem);
        personaCem.accumulate("ID_DIRECCION", 1);
        personaCem.accumulate("RUT", "105549695");
        personaCem.accumulate("NOMBRE", "Paola");
        personaCem.accumulate("APP_PATERNO", "Echavarría");
        personaCem.accumulate("APP_MATERNO", "Baraona");
        personaCem.accumulate("FECH_NACIMIENTO", "13/04/1996");
        ArrayList<Persona> prsnCem = r.requestController("POST", "persona", "persona", personaCem, Persona.class, "");
        
        JSONObject cemCM = new JSONObject();
        cemCM.accumulate("ID_USUARIO", 2);
        cemCM.accumulate("NOM_CENTRO", "Montreal");
        
        r.requestController("POST", "cem", "cem", cemCM, CEM.class, "");
        
        
    }
}
