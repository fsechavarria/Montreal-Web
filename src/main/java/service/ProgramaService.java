package service;

import entities.Alumno;
import entities.AuthUser;
import entities.CEL;
import entities.CEM;
import entities.Postulacion;
import entities.Programa_Estudio;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import util.Requests;

@Service
public class ProgramaService {
    
    private Requests req;
    
    public ArrayList getProgramas(String token) {
        req = new Requests();
        
        ArrayList<Programa_Estudio> programas = req.requestController("GET", "private/programa", "programa", null, Programa_Estudio.class, token);
        ArrayList<CEL> lstCel = req.requestController("GET", "private/cel", "cel", null, CEL.class, token);
        
        if (programas != null && !programas.isEmpty() && lstCel != null && !lstCel.isEmpty()) { 
            int index = 0;
            for (Programa_Estudio programa : programas) {
                for (CEL cel : lstCel) {
                    if (cel.getId_cel().equals(programa.getId_cel())) {
                        programa.setCel(cel);
                        programas.set(index, programa);
                        break;
                    }
                }
                index++;
            }
        }
        
        ArrayList arr = filtrarProgramas(programas);
        
        return arr;
    }
    
    public String getNombrePrograma(String token, String id) {
        req = new Requests();
        
        ArrayList<Programa_Estudio> prog = req.requestController("GET", "private/programa/" + id, "programa", null, Programa_Estudio.class, token);
        
        if (prog == null || prog.isEmpty()) {
            return null;
        }
        
        return prog.get(0).getNomb_programa();
    }
    
    public ArrayList<Programa_Estudio> getProgramas(String token, String id_usuario) {
        req = new Requests();
        
        ArrayList<Alumno> alumno = req.requestController("GET", "private/alumno?id_usuario=" + id_usuario, "alumno", null, Alumno.class, token);
        if (alumno == null || alumno.isEmpty()) {
            return new ArrayList();
        }
        
        String id_alumno = alumno.get(0).getId_alumno().toString();
        
        ArrayList<Postulacion> postulaciones = req.requestController("GET", "private/postulacion?id_alumno=" + id_alumno, "postulacion", null, Postulacion.class, token);
        ArrayList<Programa_Estudio> programas = req.requestController("GET", "private/programa", "programa", null, Programa_Estudio.class, token);
        
        ArrayList<Programa_Estudio> programasFiltrados = new ArrayList();
        if (postulaciones != null && programas != null && !programas.isEmpty() && !postulaciones.isEmpty()) {
            int max = postulaciones.size();
            int index = 0;
            int found = 0;
            for (Programa_Estudio pr : programas) {
                if (found == postulaciones.size()) {
                    break;
                }
                index = 0;
                for(Postulacion po : postulaciones) {
                    index++;
                    if (pr.getId_programa().equals(po.getId_programa())) {
                        found++;
                        break;
                    }
                    
                    if (index == postulaciones.size()) {
                        programasFiltrados.add(pr);
                    }
                }
            }
        } else if (programas == null || programas.isEmpty()){
            programasFiltrados = new ArrayList();
        } else {
            programasFiltrados = programas;
        }
        
        ArrayList<CEL> lstCel = req.requestController("GET", "private/cel", "cel", null, CEL.class, token);
        if (lstCel != null && !lstCel.isEmpty()) { 
            int index = 0;
            for (Programa_Estudio programa : programasFiltrados) {
                for (CEL cel : lstCel) {
                    if (cel.getId_cel().equals(programa.getId_cel())) {
                        programa.setCel(cel);
                        programasFiltrados.set(index, programa);
                        break;
                    }
                }
                index++;
            }
        }
        
        ArrayList arr = filtrarProgramas(programasFiltrados);
        
        return (ArrayList<Programa_Estudio>)arr.get(0);
    }
    
    public ArrayList<Programa_Estudio> getProgramasCEL(String token, String id_usuario){
        req = new Requests();
        
        ArrayList<CEL> cel = req.requestController("GET", "private/cel?id_usuario" + id_usuario, "cel", null, CEL.class, token);
        CEL cl = new CEL();
        if (cel != null && !cel.isEmpty()) {
            cl = cel.get(0);
        }
        String id_cel = cl.getId_cel().toString();
        ArrayList<Programa_Estudio> programas = req.requestController("GET", "private/programa?id_cel=" + id_cel, "programa", null, Programa_Estudio.class, token);
        
        if (programas != null) {
            int index = 0;
            for(Programa_Estudio p : programas) {
                p.setCel(cl);
                programas.set(index, p);
                index++;
            }
            return programas;
        }
        
        return null;
    }
    
    public boolean unirsePrograma(String token, String id_usuario, String id_programa){
        req = new Requests();
        
        ArrayList<CEL> cels = req.requestController("GET", "private/cel?id_usuario=" + id_usuario, "cel", null, CEL.class, token);
        CEL cel = new CEL();
        if (cels != null && !cels.isEmpty()) {
            cel = cels.get(0);
        }
        
        if (cel.getId_cel() != 0) {
            JSONObject obj = new JSONObject();
            obj.accumulate("ID_CEL", cel.getId_cel());
            ArrayList<Programa_Estudio> programa = req.requestController("PUT", "private/programa/" + id_programa, "programa", obj, Programa_Estudio.class, token);
            
            if (programa != null && !programa.isEmpty()) {
                return true;
            }
        }
        
        return false;
    }
    
    private ArrayList filtrarProgramas(ArrayList<Programa_Estudio> programas){
        Date fecha = new Date();
        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String fechAux = format.format(fecha);
            fecha = format.parse(fechAux);
        } catch (ParseException ex) {
            System.out.println(ex);
        }
        
        ArrayList<Programa_Estudio> finalizados = new ArrayList<>();
        ArrayList<Programa_Estudio> vigentes = new ArrayList<>();
        
        if (programas != null && !programas.isEmpty()) {
            for(Programa_Estudio p : programas){
                if (p.getFech_termino().compareTo(fecha) < 0) {
                    finalizados.add(p);
                } else {
                    vigentes.add(p);
                }
            }
        }
        
        ArrayList arr = new ArrayList();
        arr.add(vigentes);
        arr.add(finalizados);
        
        return arr;
    }
    
    public Programa_Estudio getPrograma(String token, String id) {
        if (id.length() == 0) {
            return null;
        }
        req = new Requests();
        
        ArrayList<Programa_Estudio> lstProgramas;
        lstProgramas = req.requestController("GET", "private/programa/" + id, "programa", null, Programa_Estudio.class, token);
        
        if (lstProgramas != null && lstProgramas.size() > 0) {
            if (lstProgramas.get(0).getId_cel() != null) {
                ArrayList<CEL> lstCEL = req.requestController("GET", "private/cel/" + lstProgramas.get(0).getId_cel(), "cel", null, CEL.class, token);
                if (lstCEL != null && lstCEL.size() > 0) {
                    CEL cel = lstCEL.get(0);
                    lstProgramas.get(0).setCel(cel);
                }
            }
            return lstProgramas.get(0);
        }
        return null;
    }
    
    public boolean updatePrograma(String token, Programa_Estudio programa) {
        req = new Requests();
        
        JSONObject jObj = new JSONObject();
        jObj.accumulate("ID_CEM", programa.getId_cem());
        jObj.accumulate("ID_CEL", programa.getId_cel());
        jObj.accumulate("NOMB_PROGRAMA", programa.getNomb_programa());
        jObj.accumulate("DESC_PROGRAMA", programa.getDesc_programa());
        
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String fech_inicio = format.format(programa.getFech_inicio());
        String fech_termino = format.format(programa.getFech_termino());
        jObj.accumulate("FECH_INICIO", fech_inicio);
        jObj.accumulate("FECH_TERMINO", fech_termino);
        
        jObj.accumulate("CANT_MIN_ALUMNOS", programa.getCant_min_alumnos());
        jObj.accumulate("CANT_MAX_ALUMNOS", programa.getCant_min_alumnos());
        
        ArrayList<Programa_Estudio> lstProg = req.requestController("PUT", "private/programa/" + programa.getId_programa(), "programa", jObj, Programa_Estudio.class, token);
        
        if (lstProg != null && lstProg.size() > 0) {
            return true;
        }
        
        return false;
    }

    public boolean deletePrograma(String token, String id) {
        if (id.length() == 0) {
            return false;
        }
        req = new Requests();
        
        ArrayList<Programa_Estudio> lstProg = req.requestController("DELETE", "private/programa/" + id, "programa", null, Programa_Estudio.class, token);
        
        if (lstProg != null && lstProg.size() > 0) {
            return true;
        }
        
        return false;
    }
    
    public boolean savePrograma(AuthUser usr, String token, Programa_Estudio programa){
        req = new Requests();
        
        ArrayList<CEM> lstCEM;
        Integer id_cem = null;
        if (usr.getRol().equals("CEM")){
            lstCEM = req.requestController("GET", "private/cem?id_usuario=" + usr.getId(), "cem", null, CEM.class, token);
            if (lstCEM != null && lstCEM.size() > 0) {
                id_cem = lstCEM.get(0).getId_cem();
            }
        } else {
            id_cem = programa.getId_cem();
        }
        
        JSONObject jObj = new JSONObject();

        jObj.accumulate("ID_CEM", id_cem);
        jObj.accumulate("NOMB_PROGRAMA", programa.getNomb_programa());
        jObj.accumulate("DESC_PROGRAMA", programa.getDesc_programa());

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String fech_inicio = format.format(programa.getFech_inicio());
        String fech_termino = format.format(programa.getFech_termino());
        jObj.accumulate("FECH_INICIO", fech_inicio);
        jObj.accumulate("FECH_TERMINO", fech_termino);

        jObj.accumulate("CANT_MIN_ALUMNOS", programa.getCant_min_alumnos());
        jObj.accumulate("CANT_MAX_ALUMNOS", programa.getCant_min_alumnos());

        ArrayList<Programa_Estudio> lstProg = req.requestController("POST", "private/programa", "programa", jObj, Programa_Estudio.class, token);

        if (lstProg != null && lstProg.size() > 0) {
            return true;
        }
        
        
        return false;
    }
}
