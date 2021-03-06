package service;

import entities.Alumno;
import entities.Calificacion;
import entities.Curso;
import entities.Programa_Estudio;
import java.util.ArrayList;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import util.Requests;

@Service
public class CalificacionService {
    
    private Requests req;
    
    public ArrayList<Calificacion> getCalificaciones(String token){
        req = new Requests();
        CursoService cs = new CursoService();
        AlumnoService as = new AlumnoService();
        
        ArrayList<Calificacion> calificaciones = req.requestController("GET", "private/calificacion", "calificacion", null, Calificacion.class, token);
        
        if (calificaciones == null || calificaciones.isEmpty()) {
            return new ArrayList();
        }
        
        ArrayList<Alumno> alumnos = as.getAlumnos(token);
        
        ArrayList<Curso> cursos = cs.getCursos(token);
        ArrayList<Calificacion> vig_calif = new ArrayList();
        
        for(Calificacion c : calificaciones) {
            for(Alumno al : alumnos) {
                if (al.getId_alumno().equals(c.getId_alumno())) {
                    c.setAlumno(al);
                    break;
                }
            }
            
            for (Curso cur : cursos) {
                if (c.getId_curso().equals(cur.getId_curso())){
                    c.setCurso(cur);
                    vig_calif.add(c);
                    break;
                }
            }
        }
        
        return vig_calif;
    }
    
    public ArrayList<Calificacion> getCalificacionesAlumno(String token, String id_alumno, String id_programa){
        if (id_programa == null) {
            return null;
        }
        req = new Requests();
        
        ArrayList<Curso> cursos = req.requestController("GET", "private/curso?id_programa=" + id_programa, "curso", null, Curso.class, token);
        if (cursos == null || cursos.isEmpty()) {
            return null;
        }
        ArrayList<Calificacion> calificaciones = req.requestController("GET", "private/calificacion?id_alumno=" + id_alumno, "calificacion", null, Calificacion.class, token);
        if (calificaciones == null || calificaciones.isEmpty()) {
            return null;
        }
        
        ArrayList<Calificacion> notas = new ArrayList();
        for(Curso c : cursos) {
            for(Calificacion cal : calificaciones) {
                if (c.getId_curso().equals(cal.getId_curso())){
                    cal.setCurso(c);
                    notas.add(cal);
                }
            }
        }
        
        return notas;
    }
    
    public ArrayList<Calificacion> getCalificaciones(String token, String id_usuario){
        req = new Requests();
        CursoService cs = new CursoService();
        AlumnoService as = new AlumnoService();
        
        Alumno alumno = as.getAlumno(token, id_usuario, true);
        
        String id_alumno = alumno != null ? alumno.getId_alumno().toString() : "";
        
        ArrayList<Calificacion> calificaciones = req.requestController("GET", "private/calificacion?id_alumno=" + id_alumno, "calificacion", null, Calificacion.class, token);
        
        if (calificaciones == null || calificaciones.isEmpty()) {
            return new ArrayList();
        }
        ArrayList<Curso> cursos = cs.getCursos(token);
        ArrayList<Calificacion> vig_calif = new ArrayList();
        
        for(Calificacion c : calificaciones) {
            for (Curso cur : cursos) {
                if (c.getId_curso().equals(cur.getId_curso())){
                    c.setCurso(cur);
                    c.setAlumno(alumno);
                    vig_calif.add(c);
                    break;
                }
            }
        }
        
        return vig_calif;
    }
    
    public boolean saveCalificacion(String token, Calificacion calificacion) {
        req = new Requests();
        
        JSONObject obj = new JSONObject();
        obj.accumulate("ID_ALUMNO", calificacion.getId_alumno());
        obj.accumulate("ID_CURSO", calificacion.getId_curso());
        obj.accumulate("NOTA", calificacion.getNota());
        
        ArrayList<Calificacion> cal = req.requestController("POST", "private/calificacion", "calificacion", obj, Calificacion.class, token);
        
        
        return !(cal == null || cal.isEmpty());
    }
}
