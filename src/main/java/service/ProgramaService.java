package service;

import entities.AuthUser;
import entities.CEL;
import entities.CEM;
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
public class ProgramaService {
    
    private Requests req;
    
    public ArrayList getProgramas(String token) {
        req = new Requests();
        Date fecha = new Date();
        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String fechAux = format.format(fecha);
            fecha = format.parse(fechAux);
        } catch (ParseException ex) {
            System.out.println(ex);
        }
        
        ArrayList<Programa_Estudio> lstProgramas = req.requestController("GET", "private/programa", "programa", null, Programa_Estudio.class, token);
        ArrayList<CEL> lstCel = req.requestController("GET", "private/cel", "cel", null, CEL.class, token);
        
        if (lstCel != null && lstCel.size() > 0) { 
            for(int i = 0; i < lstProgramas.size(); i++) {
                for (CEL cel : lstCel) {
                    if (Objects.equals(cel.getId_cel(), lstProgramas.get(i).getId_cel())) {
                        lstProgramas.get(i).setCel(cel);
                    }
                }
            }
        }
        
        ArrayList<Programa_Estudio> lstProgramasFinalizados = new ArrayList<>();
        if (lstProgramas != null && lstProgramas.size() > 0) {
            for(int i = 0; i < lstProgramas.size(); i++) {
                if (lstProgramas.get(i).getFech_termino().compareTo(fecha) < 0) {
                    lstProgramasFinalizados.add(lstProgramas.get(i));
                    lstProgramas.remove(lstProgramas.get(i));
                }
            }
        }
        
        ArrayList arr = new ArrayList<>();
        arr.add(lstProgramas);
        arr.add(lstProgramasFinalizados);
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
        
        ArrayList<CEM> lstCEM = req.requestController("GET", "private/cem?id_usuario=" + usr.getId(), "cem", null, CEM.class, token);
        
        Integer id_cem = null;
        if (lstCEM != null && lstCEM.size() > 0) {
            id_cem = lstCEM.get(0).getId_cem();
        }
        
        if (id_cem != null) {
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
        }
        
        return false;
    }
}
