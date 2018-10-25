package service;

import entities.Alumno;
import entities.Familia;
import entities.Postulacion;
import entities.Programa_Estudio;
import entities.Seguro;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import util.Requests;

@Service
public class PostulacionService {
    
    private Requests req;
    
    public ArrayList getPostulaciones(String token, String id) {
        req = new Requests();
        
        ArrayList<Seguro> lstSeguro = new ArrayList();
        ArrayList<Postulacion> tmp_postulacion = new ArrayList();
        if (id == null || id.length() == 0) {
            lstSeguro = req.requestController("GET", "private/seguro", "seguro", null, Seguro.class, token);
            tmp_postulacion = req.requestController("GET", "private/postulacion", "postulacion", null, Postulacion.class, token);
        } else {
            ArrayList<Alumno> alm = req.requestController("GET", "private/alumno?id_usuario=" + id, "alumno", null, Alumno.class, token);
            Alumno a = new Alumno();
            if (alm != null && !alm.isEmpty()) a = alm.get(0);
            
            lstSeguro = req.requestController("GET", "private/seguro", "seguro", null, Seguro.class, token);
            tmp_postulacion = req.requestController("GET", "private/postulacion?id_alumno="+a.getId_alumno(), "postulacion", null, Postulacion.class, token);
        }
         
        
        if (tmp_postulacion == null || tmp_postulacion.isEmpty()) { 
            return new ArrayList();
        }
        
        ProgramaService ps = new ProgramaService();
        ArrayList lstProgramas = ps.getProgramas(token);
        
        ArrayList<Programa_Estudio> vigentes = new ArrayList();
        ArrayList<Programa_Estudio> finalizados = new ArrayList();
        ArrayList<Alumno> lstAlumnos = new ArrayList();
        if (lstProgramas != null && lstProgramas.size() == 2) {
            AlumnoService as = new AlumnoService();
            lstAlumnos = as.getAlumnos(token);
            vigentes = (ArrayList)lstProgramas.get(0);
            finalizados = (ArrayList)lstProgramas.get(1);
        }
        
        
        ArrayList<Postulacion> lstPostulacion = new ArrayList();
        ArrayList<Postulacion> lstFinalizadas = new ArrayList();
        Seguro seg = null;
        if (lstSeguro != null && !lstSeguro.isEmpty()) {
            seg = (Seguro)lstSeguro.get(0);
        }
        
        boolean vigente;
        for(Postulacion p : tmp_postulacion) {
            vigente = false;
            for(Programa_Estudio prog : vigentes) {
                if (p.getId_programa().equals(prog.getId_programa()) && p.getEstado() == "P") {
                    p.setSeguro(seg);
                    p.setPrograma(prog);
                    lstPostulacion.add(p);
                    vigente = true;
                    break;
                }
            }
            
            if (!vigente) {
                for(Programa_Estudio prog : finalizados) {
                    
                    if (p.getEstado() == "R") {
                        p.setSeguro(seg);
                        p.setPrograma(prog);
                        lstFinalizadas.add(p);
                        break;
                    }
                    
                    if (p.getEstado() == "A") {
                        p.setSeguro(seg);
                        p.setPrograma(prog);
                        lstFinalizadas.add(p);
                        break;
                    }
                    
                    if (p.getId_programa().equals(prog.getId_programa())) {
                        p.setSeguro(seg);
                        if (p.getEstado() == "P") {
                            JSONObject obj = new JSONObject();
                            obj.accumulate("ESTADO", 'R');
                            if (p.getFech_respuesta() == null) {
                                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                                String fecha = format.format(new Date());
                                obj.accumulate("FECH_RESPUESTA", fecha);
                            }
                            req.requestController("PUT", "private/postulacion/" + p.getId_postulacion(), "postulacion", obj, Postulacion.class, token);
                            p.setEstado("R");
                        }
                        p.setPrograma(prog);
                        lstFinalizadas.add(p);
                        break;
                    }
                    
                }
            }
        }
        
        int index = 0;
        for(Alumno a : lstAlumnos) {
            index = 0;
            for(Postulacion p : lstPostulacion) {
                if (a.getId_alumno().equals(p.getId_alumno())) {
                    p.setAlumno(a);
                    lstPostulacion.set(index, p);
                }
                index++;
            }
            
            index = 0;
            for(Postulacion p : lstFinalizadas) {
                if (a.getId_alumno().equals(p.getId_alumno())) {
                    p.setAlumno(a);
                    lstFinalizadas.set(index, p);
                }
                index++;
            }
        }
        
        ArrayList arr = new ArrayList<>();
        arr.add(lstPostulacion);
        arr.add(lstFinalizadas);
        
        return arr;
    }
    
    public Postulacion getPostulacion(String token, String id){
        if (id == null || id.trim().length() == 0) {
            return null;
        }
        
        req = new Requests();
        
        ArrayList<Postulacion> lstPostulacion = req.requestController("GET", "private/postulacion/" + id, "postulacion", null, Postulacion.class, token);
        
        if (lstPostulacion == null || lstPostulacion.isEmpty()) {
            return null;
        }
        Postulacion p = lstPostulacion.get(0);
        String id_programa = p.getId_programa().toString();
        String id_alumno = p.getId_alumno().toString();
        String id_familia = p.getId_familia().toString();
        String id_seguro = p.getId_seguro().toString();
        
        ProgramaService ps = new ProgramaService();
        AlumnoService as = new AlumnoService();
        FamiliaService fs = new FamiliaService();
        SeguroService ss = new SeguroService();
        
        Programa_Estudio prog = ps.getPrograma(token, id_programa);
        if (prog != null) {
            p.setPrograma(prog);
        }
        
        Familia fam = fs.getFamilia(token, id_familia);
        if (fam != null) {
            p.setFamilia(fam);
        }
        
        Alumno al = as.getAlumno(token, id_alumno);
        if (al != null) {
            p.setAlumno(al);
        }
        
        Seguro s = ss.getSeguro(token, id_seguro);
        if (s != null) {
            p.setSeguro(s);
        }
        
        
        return p;
    }
    
    public boolean answerPostulacion(String token, String id, boolean accept) {
        if (id == null || id.trim().length() == 0) {
            return false;
        }
        
        req = new Requests();
        
        JSONObject jObj = new JSONObject();
        if (accept) {
            jObj.accumulate("ESTADO", "A");
        } else {
            jObj.accumulate("ESTADO", "R");
        }
        
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date d = new Date();
        String fecha = format.format(d);
        jObj.accumulate("FECH_RESPUESTA", fecha);
        
        ArrayList<Postulacion> postulacion = req.requestController("PUT", "private/postulacion/" + id, "postulacion", jObj, Postulacion.class, token);
        
        return !(postulacion == null || postulacion.isEmpty());
    } 
}
