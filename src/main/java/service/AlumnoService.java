package service;

import entities.Alumno;
import entities.Persona;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import util.Requests;

@Service
public class AlumnoService {
    
    private Requests req; 
    
    public ArrayList getAlumnos(String token){
        req = new Requests();
        
        ArrayList<Alumno> lstAlumno = req.requestController("GET", "private/alumno", "alumno", null, Alumno.class, token);
        ArrayList<Persona> lstPersona = req.requestController("GET", "private/persona", "persona", null, Persona.class, token);
        
        int index = 0;
        for(Alumno a : lstAlumno) {
            for(Persona p : lstPersona) {
                if (a.getId_usuario().equals(p.getId_usuario())) {
                    a.setPersona(p);
                    lstAlumno.set(index, a);
                }
            }
            index++;
        }
        
        return lstAlumno;
    }
    
    public Alumno getAlumno(String token, String id){
        if (id == null || id.trim().length() == 0) {
            return null;
        }
        
        req = new Requests();
        
        ArrayList<Alumno> lstAlumno = req.requestController("GET", "private/alumno/" + id, "alumno", null, Alumno.class, token);
        
        if (lstAlumno == null || lstAlumno.isEmpty()) {
            return null;
        }
        Alumno a = lstAlumno.get(0);
        String id_usuario = a.getId_usuario().toString();
        
        ArrayList<Persona> lstPersona = req.requestController("GET", "private/persona?id_usuario=" + id_usuario, "persona", null, Persona.class, token);
        
        if (lstPersona != null && !lstPersona.isEmpty()){
            a.setPersona(lstPersona.get(0));
        }
        
        return a;
    }
}
