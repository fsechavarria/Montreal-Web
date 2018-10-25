package test;

import entities.*;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import org.json.JSONObject;
import util.*;

public class main {
    
    public static void main (String[] args) throws UnsupportedEncodingException {
        Requests r = new Requests();
        JWT j = new JWT();
        
        // Admin
        
        JSONObject admin = new JSONObject();
        admin.accumulate("ID_ROL", 1);
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
        personaAdmin.accumulate("APP_MATERNO", "Echavarria");
        personaAdmin.accumulate("FECH_NACIMIENTO", "13/04/1996");
        
        r.requestController("POST", "persona", "persona", personaAdmin, Persona.class, "");
        
        // CEM
        
        JSONObject cem = new JSONObject();
        cem.accumulate("ID_ROL", 2);
        cem.accumulate("USUARIO", "cem");
        cem.accumulate("CONTRASENA", "cem");
        ArrayList<CEM> cm = r.requestController("POST", "usuario", "usuario", cem, CEM.class, "");
        int id_usuarioCem = cm.get(0).getId_usuario();
        
        JSONObject personaCem = new JSONObject();
        personaCem.accumulate("ID_USUARIO", id_usuarioCem);
        personaCem.accumulate("ID_DIRECCION", 1);
        personaCem.accumulate("RUT", "105549695");
        personaCem.accumulate("NOMBRE", "Paola");
        personaCem.accumulate("APP_PATERNO", "Echavarria");
        personaCem.accumulate("APP_MATERNO", "Baraona");
        personaCem.accumulate("FECH_NACIMIENTO", "13/04/1985");
        ArrayList<Persona> prsnCem = r.requestController("POST", "persona", "persona", personaCem, Persona.class, "");
        
        JSONObject cemCM = new JSONObject();
        cemCM.accumulate("ID_USUARIO", id_usuarioCem);
        cemCM.accumulate("NOM_CENTRO", "Montreal");
        
        ArrayList<CEM> lstCEM = r.requestController("POST", "cem", "cem", cemCM, CEM.class, "");
        int id_cem = lstCEM.get(0).getId_cem();
        
        // CEL
        
        JSONObject usrCEL = new JSONObject();
        usrCEL.accumulate("ID_ROL", 3);
        usrCEL.accumulate("USUARIO", "cel");
        usrCEL.accumulate("CONTRASENA", "cel");
        ArrayList<CEL> cl = r.requestController("POST", "usuario", "usuario", usrCEL, CEL.class, "");
        int id_usrcel = cl.get(0).getId_usuario();
        
        JSONObject perCEL = new JSONObject();
        perCEL.accumulate("ID_USUARIO", id_usrcel);
        perCEL.accumulate("ID_DIRECCION", 1);
        perCEL.accumulate("RUT", "176808683");
        perCEL.accumulate("NOMBRE", "Camila Alejandra");
        perCEL.accumulate("APP_PATERNO", "Saa");
        perCEL.accumulate("APP_MATERNO", "Echavarria");
        perCEL.accumulate("FECH_NACIMIENTO", "13/04/1990");
        
        r.requestController("POST", "persona", "persona", perCEL, Persona.class, "");
        
        JSONObject cel = new JSONObject();
        cel.accumulate("ID_USUARIO", id_usrcel);
        cel.accumulate("NOM_CENTRO", "CEL Mont");
        
        ArrayList<CEL> lstCEL = r.requestController("POST", "cel", "cel", cel, CEL.class, "");
        int id_cel = lstCEL.get(0).getId_cel();
        
        // Programa de Estudio
        
        JSONObject prog = new JSONObject();
        prog.accumulate("ID_CEM", 1);
        prog.accumulate("ID_CEL", 1);
        prog.accumulate("NOMB_PROGRAMA", "Programa de Pruebas");
        prog.accumulate("DESC_PROGRAMA", "Descripcion del programa de pruebas");
        prog.accumulate("FECH_INICIO", "10/10/2016");
        prog.accumulate("FECH_TERMINO", "10/10/2019");
        prog.accumulate("CANT_MIN_ALUMNOS", "20");
        prog.accumulate("CANT_MAX_ALUMNOS", "50");
        
        r.requestController("POST", "programa", "programa", prog, Programa_Estudio.class, "");
        
        
    }
}
