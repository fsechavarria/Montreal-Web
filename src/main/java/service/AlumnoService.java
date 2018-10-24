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
}
