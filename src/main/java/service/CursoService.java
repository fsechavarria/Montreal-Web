package service;

import entities.Curso;
import entities.Programa_Estudio;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import util.Requests;

@Service
public class CursoService {
    
    private Requests req;
    
    public ArrayList getCursos(String token, String id_programa){
        req = new Requests();
        ArrayList<Curso> lstCursos = req.requestController("GET", "private/curso", "curso", null, Curso.class, token);
        
        if (lstCursos == null) {
            return new ArrayList<>();
        }
        
        ArrayList<Programa_Estudio> lstPrograma = req.requestController("GET", "private/programa/"+id_programa, "programa", null, Programa_Estudio.class, token);
        Programa_Estudio prog = new Programa_Estudio();
        if (lstPrograma != null && !lstPrograma.isEmpty()){
            prog = lstPrograma.get(0);
        }
        ArrayList<Curso> cursos_aux = new ArrayList();
        for(Curso c : lstCursos) {
            if (c.getId_programa().equals(prog.getId_programa())){
                cursos_aux.add(c);
            }
        }
        
        
        return cursos_aux;
    }
    
    public ArrayList getCursos(String token){
        req = new Requests();
        ArrayList<Curso> lstCursos = req.requestController("GET", "private/curso", "curso", null, Curso.class, token);

        if (lstCursos == null) {
            return new ArrayList<>();
        }
        
        ArrayList<Programa_Estudio> lstPrograma = req.requestController("GET", "private/programa", "programa", null, Programa_Estudio.class, token);
        
        Date fecha = new Date();
        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String fechAux = format.format(fecha);
            fecha = format.parse(fechAux);
        } catch (ParseException ex) {
            System.out.println(ex);
        }
        
        if (lstPrograma != null && lstPrograma.size() > 0) {
            for(int i = 0; i < lstCursos.size(); i++) {
                for(Programa_Estudio p : lstPrograma) {
                    if (Objects.equals(lstCursos.get(i).getId_programa(), p.getId_programa())) {
                        lstCursos.get(i).setPrograma(p);
                    }
                }
            }
            
            for(Curso c : lstCursos) {
                if (c.getPrograma() != null && c.getPrograma().getFech_termino().compareTo(fecha) < 0) {
                    lstCursos.remove(c);
                }
            }
        }
        
        return lstCursos;
    }
    
    public Curso getCurso(String token, String id) {
        if (id.length() == 0) {
            return null;
        }
        req = new Requests();
        ArrayList<Curso> lstCursos = req.requestController("GET", "private/curso/" + id, "curso", null, Curso.class, token);
        
        if (lstCursos == null || lstCursos.isEmpty()) {
            return null;
        }
        
        Curso curso = lstCursos.get(0);
        
        return curso;
    }
    
    public boolean saveCurso(String token, Curso curso){
        req = new Requests();

        JSONObject jObj = new JSONObject();

        jObj.accumulate("DESC_CURSO", curso.getDesc_curso());
        jObj.accumulate("CUPOS", curso.getCupos());
        jObj.accumulate("ID_PROGRAMA", curso.getId_programa());

        ArrayList<Curso> lstCursos = req.requestController("POST", "private/curso", "curso", jObj, Curso.class, token);
        
        if (lstCursos == null || lstCursos.isEmpty()) {
            return false;
        }
        return true;
    }
    
    public boolean updateCurso(String token, Curso curso) {
        req = new Requests();
        
        JSONObject jObj = new JSONObject();
        jObj.accumulate("DESC_CURSO", curso.getDesc_curso());
        jObj.accumulate("CUPOS", curso.getCupos());
        jObj.accumulate("ID_PROGRAMA", curso.getId_programa());
        
        ArrayList<Curso> lstCurso = req.requestController("PUT", "private/curso/" + curso.getId_curso(), "curso", jObj, Curso.class, token);
        
        if (lstCurso == null || lstCurso.isEmpty()) {
            return false;
        }
        
        return true;
    }
    
    public boolean deleteCurso(String token, String id){
        if (id.length() == 0) {
            return false;
        }
        
        req = new Requests();
        
        ArrayList<Curso> lstCurso = req.requestController("DELETE", "private/curso/" + id, "curso", null, Curso.class, token);
        
        if (lstCurso == null || lstCurso.isEmpty()) {
            return false;
        }
        
        return true;
    }
}
