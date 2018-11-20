package service;

import entities.Alumno;
import entities.Direccion;
import entities.Inscripcion_Alumno;
import entities.Persona;
import entities.Rol;
import entities.Usuario;
import java.util.ArrayList;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import util.Requests;

@Service
public class AlumnoService {
    
    private Requests req; 
    
    public ArrayList getAlumnos(String token, String id_programa){
        req = new Requests();
        
        ArrayList<Inscripcion_Alumno> inscripciones = req.requestController("GET", "private/inscripcion?id_programa=" + id_programa, "inscripcion", null, Inscripcion_Alumno.class, token);
        
        if (inscripciones == null || inscripciones.isEmpty()) {
            return null;
        }
        
        ArrayList<Alumno> tmp_alumno = req.requestController("GET", "private/alumno", "alumno", null, Alumno.class, token);
        
        if (tmp_alumno == null) {
            return null;
        }
        
        UsuarioService us = new UsuarioService();
        ArrayList<Usuario> usuarios = us.getUsuarios(token);
        
        // Asignar a la nueva lista del alumnos solo los pertenecientes al programa seleccionado
        ArrayList<Alumno> lstAlumno = new ArrayList();
        for (Inscripcion_Alumno ins : inscripciones) {
            for (Alumno a : tmp_alumno) {
                if (ins.getId_alumno().equals(a.getId_alumno())){
                    if (!lstAlumno.contains(a)) lstAlumno.add(a);
                    break;
                }
            }
        }
        
        // Asignar a la lista de alumnos sus correspondientes datos de persona/usuario
        int index = 0;
        for(Alumno a : lstAlumno) {
            for(Usuario u : usuarios) {
                if (a.getId_usuario().equals(u.getId_usuario())) {
                    a.setUsuario(u);
                    lstAlumno.set(index, a);
                    break;
                }
            }
            index++;
        }
        
        return lstAlumno;
    }
    
    public ArrayList getAlumnos(String token){
        req = new Requests();
        
        ArrayList<Alumno> lstAlumno = req.requestController("GET", "private/alumno", "alumno", null, Alumno.class, token);
        ArrayList<Persona> lstPersona = req.requestController("GET", "private/persona", "persona", null, Persona.class, token);
        
        int index = 0;
        if (lstAlumno != null && lstPersona != null) {
            for(Alumno a : lstAlumno) {
                for(Persona p : lstPersona) {
                    if (a.getId_usuario().equals(p.getId_usuario())) {
                        a.setPersona(p);
                        lstAlumno.set(index, a);
                    }
                }
                index++;
            }
        } else {
            lstAlumno = new ArrayList();
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
    
    public Alumno getAlumno(String token, String id_usuario, boolean usuario){
        req = new Requests();
        
        ArrayList<Alumno> lstAlumno = req.requestController("GET", "private/alumno?id_usuario=" + id_usuario, "alumno", null, Alumno.class, token);
        
        if (lstAlumno == null || lstAlumno.isEmpty()) {
            return null;
        }
        Alumno a = lstAlumno.get(0);
        
        ArrayList<Persona> lstPersona = req.requestController("GET", "private/persona?id_usuario=" + id_usuario, "persona", null, Persona.class, token);
        
        if (lstPersona != null && !lstPersona.isEmpty()){
            a.setPersona(lstPersona.get(0));
        }
        
        return a;
    }
    
    public String getNombreAlumno(String token, String id_alumno){
        req = new Requests();
        
        ArrayList<Alumno> lstAlumno = req.requestController("GET", "private/alumno/" + id_alumno, "alumno", null, Alumno.class, token);
        
        if (lstAlumno == null || lstAlumno.isEmpty()) {
            return null;
        }
        Alumno a = lstAlumno.get(0);
        String id_usuario = a.getId_usuario().toString();
        
        ArrayList<Persona> lstPersona = req.requestController("GET", "private/persona?id_usuario=" + id_usuario, "persona", null, Persona.class, token);
        
        if (lstPersona != null && !lstPersona.isEmpty()){
            a.setPersona(lstPersona.get(0));
        }
        
        String nombre = a.getPersona().getNombre() + " " + a.getPersona().getApp_paterno() + " " + a.getPersona().getApp_materno();
        
        return nombre;
    }
    
    public String saveAlumno(Alumno al){
        UsuarioService us = new UsuarioService();
        ContactoService cs = new ContactoService();
        PersonaService ps = new PersonaService();
        req = new Requests();
        
        if (us.usuarioExists(al.getUsuario().getUsuario())) {
            return "El usuario ingresado ya está registrado.";
        }
        
        if (ps.rutExists(al.getUsuario().getPersona().getRut())) {
            return "El rut ingresado ya está registrado.";
        }
        
        ArrayList<Rol> lstRol = req.requestController("GET", "rol", "rol", null, Rol.class, "");
        Integer id_rol = 0;
        if (lstRol != null && !lstRol.isEmpty()) {
            for(Rol r : lstRol) {
                if (r.getDesc_rol().equals("Alumno")) {
                    id_rol = r.getId_rol();
                    break;
                }
            }
        }
        
        al.getUsuario().setId_rol(id_rol);
        Usuario u = us.saveUsuario(al.getUsuario());
        
        if (u != null) {
            JSONObject obj = new JSONObject();
            obj.accumulate("ID_USUARIO", u.getId_usuario());
            
            ArrayList<Alumno> alumno = req.requestController("POST", "alumno", "alumno", obj, Alumno.class, "");
            
            if (alumno == null || alumno.isEmpty()) {
                // Se elimina todo en caso de error, para evitar datos duplicados y problemas en intentos subsiguientes de registro.
                us.deleteUsuario(u.getId_usuario().toString());
                req.requestController("DELETE", "direccion/" + u.getPersona().getId_persona().toString(), "direccion", null, Direccion.class, "");
                cs.deleteContactos(u.getPersona().getId_persona().toString());
                return "Error al registrar Alumno.";
            }
            return null;
        }
        
        return "Error al registrar usuario.";
    }
}
