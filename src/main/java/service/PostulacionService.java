package service;

import entities.Alumno;
import entities.Contacto;
import entities.Familia;
import entities.Inscripcion_Alumno;
import entities.Persona;
import entities.Postulacion;
import entities.Programa_Estudio;
import entities.Seguro;
import java.io.UnsupportedEncodingException;
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
    
    public ArrayList getPostulaciones(String token, String id_usuario) {
        req = new Requests();
        SeguroService sS = new SeguroService();
        
        ArrayList<Seguro> lstSeguro = req.requestController("GET", "private/seguro", "seguro", null, Seguro.class, token);
        ArrayList<Postulacion> tmp_postulacion = new ArrayList();
        
        if (id_usuario == null || id_usuario.length() == 0) {
            tmp_postulacion = req.requestController("GET", "private/postulacion", "postulacion", null, Postulacion.class, token);
        } else {
            ArrayList<Alumno> alm = req.requestController("GET", "private/alumno?id_usuario=" + id_usuario, "alumno", null, Alumno.class, token);
            
            Alumno a = new Alumno();
            if (alm != null && !alm.isEmpty()) a = alm.get(0);
            
            tmp_postulacion = req.requestController("GET", "private/postulacion?id_alumno="+a.getId_alumno(), "postulacion", null, Postulacion.class, token);
        }
         
        
        if (tmp_postulacion == null || tmp_postulacion.isEmpty()) { 
            return new ArrayList();
        }
        
        AlumnoService as = new AlumnoService();
        FamiliaService fs = new FamiliaService();
        ArrayList<Alumno> lstAlumnos = as.getAlumnos(token);
        ArrayList<Familia> familias = fs.getFamilias(token);
        
        // Se agregan las familias y los alumnos a las postulaciones
        int index = 0;
        for(Postulacion p : tmp_postulacion) {
            for(Familia f : familias) {
                if (p.getId_familia().equals(f.getId_familia())) {
                    p.setFamilia(f);
                    tmp_postulacion.set(index, p);
                    break;
                }
            }
            
            for (Alumno a : lstAlumnos) {
                if (p.getId_alumno().equals(p.getId_alumno())) {
                    p.setAlumno(a);
                    tmp_postulacion.set(index, p);
                    break;
                }
            }
            index++;
        }
        
        Seguro seg = null;
        if (lstSeguro != null && !lstSeguro.isEmpty()) {
            seg = (Seguro)lstSeguro.get(0);
        }
        
        ProgramaService ps = new ProgramaService();
        ArrayList lstProgramas = ps.getProgramas(token);
        
        ArrayList<Programa_Estudio> vigentes = (ArrayList)lstProgramas.get(0);
        ArrayList<Programa_Estudio> finalizados = (ArrayList)lstProgramas.get(1);
        
        boolean vigente;
        index = 0;
        for(Postulacion p : tmp_postulacion) {
            vigente = false;
            for(Programa_Estudio prog : vigentes) {
                p.setSeguro(seg);
                p.setPrograma(prog);
                tmp_postulacion.set(index, p);
                vigente = true;
                break;
            }
            
            if (!vigente) {
                for(Programa_Estudio prog : finalizados) {
                  p.setSeguro(seg);
                  p.setPrograma(prog);
                  tmp_postulacion.set(index, p);
                  break;
                }
            }
            index++;
        }
        
        ArrayList arr = this.filtrarPostulaciones(tmp_postulacion, lstProgramas, token);
        ArrayList<Postulacion> lstPostulacion = (ArrayList<Postulacion>)arr.get(0);
        ArrayList<Postulacion> lstFinalizadas = (ArrayList<Postulacion>)arr.get(1);
        
        arr = new ArrayList<>();
        arr.add(lstPostulacion);
        arr.add(lstFinalizadas);
        
        return arr;
    }
    
    private ArrayList filtrarPostulaciones(ArrayList<Postulacion> postulaciones, ArrayList programas, String token){
        
        ArrayList<Programa_Estudio> vigentes = (ArrayList)programas.get(0);
        ArrayList<Programa_Estudio> finalizados = (ArrayList)programas.get(1);
        
        ArrayList<Postulacion> lstPostulacion = new ArrayList();
        ArrayList<Postulacion> lstFinalizadas = new ArrayList();
        
        if (postulaciones != null && !postulaciones.isEmpty()) {
            boolean vigente;
            for(Postulacion p : postulaciones) {
                vigente = false;
                for(Programa_Estudio prog : vigentes) {
                    if (p.getId_programa().equals(prog.getId_programa()) && p.getEstado().equals("P")) {
                        lstPostulacion.add(p);
                        vigente = true;
                        break;
                    }
                }

                if (!vigente) {
                    for(Programa_Estudio prog : finalizados) {

                        if (p.getEstado().equals("R")) {
                            lstFinalizadas.add(p);
                            break;
                        }

                        if (p.getEstado().equals("A")) {
                            lstFinalizadas.add(p);
                            break;
                        }

                        if (p.getId_programa().equals(prog.getId_programa())) {
                            if (p.getEstado().equals("P")) {
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
                            lstFinalizadas.add(p);
                            break;
                        }

                    }
                }
            }
        }
        
        ArrayList arr = new ArrayList();
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
        String estado;
        if (accept) {
            jObj.accumulate("ESTADO", "A");
            estado = "aceptada";
        } else {
            jObj.accumulate("ESTADO", "R");
            estado = "rechazada";
        }
        
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date d = new Date();
        String fecha = format.format(d);
        jObj.accumulate("FECH_RESPUESTA", fecha);
        
        ArrayList<Postulacion> postulacion = req.requestController("PUT", "private/postulacion/" + id, "postulacion", jObj, Postulacion.class, token);
        
        if (postulacion == null || postulacion.isEmpty()) {
            return false;
        }
        
        String id_alumno = postulacion.get(0).getId_alumno().toString();
        String id_programa = postulacion.get(0).getId_programa().toString();
        
        if (accept) {
            JSONObject obj = new JSONObject();
            obj.accumulate("ID_PROGRAMA", id_programa);
            obj.accumulate("ID_ALUMNO", id_alumno);
            ArrayList<Inscripcion_Alumno> inscripciones = req.requestController("POST", "private/inscripcion", "inscripcion", obj, Inscripcion_Alumno.class, token);
        }
        // Envio de correo
        String fecha_respuesta = format.format(postulacion.get(0).getFech_respuesta());
        ArrayList<Programa_Estudio> programa = req.requestController("GET", "private/programa/"+id_programa, "programa", null, Programa_Estudio.class, token);
        String nom_programa = "";
        if (programa != null && !programa.isEmpty()) {
            nom_programa = programa.get(0).getNomb_programa();
        }
        
        envioCorreo(id_alumno, estado, nom_programa, fecha_respuesta, token);
        
        return true;
    }
    
    public void envioCorreo(String id_alumno, String estado, String programa, String fecha_respuesta, String token){
        req = new Requests();
        
        ArrayList<Alumno> alumno = req.requestController("GET", "private/alumno/" + id_alumno, "alumno", null, Alumno.class, token);
        
        if (alumno != null && !alumno.isEmpty()) {
            String id_usuario = alumno.get(0).getId_usuario().toString();
            ArrayList<Persona> persona = req.requestController("GET", "private/persona?id_usuario=" + id_usuario, "persona", null, Persona.class, token);
            
            if (persona != null && !persona.isEmpty()) {
                String id_persona = persona.get(0).getId_persona().toString();
                
                ArrayList<Contacto> contacto = req.requestController("GET", "private/contacto?id_persona=" + id_persona, "contacto", null, Contacto.class, token);
                if (contacto != null && !contacto.isEmpty()) {
                    try {
                        byte[] encode;
                        String mail = contacto.get(0).getDesc_contacto();
                        
                        String msg = "Estimado Alumno, \n\nHoy " + fecha_respuesta + " su postulación al programa \"" + programa + "\" ha sido " + estado;
                        encode = msg.getBytes("UTF-8");
                        msg = new String(encode);
                        
                        String subject = "Respuesta Postulación";
                        encode = subject.getBytes("UTF-8");
                        subject = new String(encode);

                        JSONObject obj = new JSONObject();
                        obj.accumulate("TO", mail);
                        obj.accumulate("SUBJECT", subject);
                        obj.accumulate("TEXT", msg);
                        req.requestController("POST", "private/email", null, obj, null, token);
                    } catch(UnsupportedEncodingException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }
    
    public boolean nuevaPostulacion(Postulacion pos, String token, String id_usuario) {
        req = new Requests();
        
        ArrayList<Alumno> alumno = req.requestController("GET", "private/alumno?id_usuario=" + id_usuario, "alumno", null, Alumno.class, token);
        if (alumno == null || alumno.isEmpty()){
            return false;
        }
        String id_alumno = alumno.get(0).getId_alumno().toString();
        
        JSONObject obj = new JSONObject();
        obj.accumulate("ID_PROGRAMA", pos.getId_programa());
        obj.accumulate("ID_FAMILIA", pos.getId_familia());
        obj.accumulate("ID_SEGURO", pos.getId_seguro());
        obj.accumulate("ID_ALUMNO", id_alumno);
        obj.accumulate("RESERVA_DINERO_PASAJES", pos.getReserva_dinero_pasajes());
        
        ArrayList<Postulacion> postulacion = req.requestController("POST", "private/postulacion", "postulacion", obj, Postulacion.class, token);
        
        return !(postulacion == null || postulacion.isEmpty());
    } 
}
