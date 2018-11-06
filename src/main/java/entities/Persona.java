package entities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Persona{
    private Integer id_persona;
    private Integer id_direccion;
    private Integer id_usuario;
    private String rut;
    private String nombre;
    private String app_paterno;
    private String app_materno;
    private Date fech_nacimiento;
    private Direccion direccion;
    private Contacto contacto;

    public Persona() {
        this.id_persona = 0;
        this.id_direccion = 0;
        this.id_usuario = 0;
        this.rut = null;
        this.nombre = null;
        this.app_paterno = null;
        this.app_materno = null;
        this.fech_nacimiento = null;
        this.direccion = new Direccion();
        this.contacto = new Contacto();
    }

    public Persona(Integer id_persona, Integer id_direccion, Integer id_usuario, String rut, String nombre, String app_paterno, 
            String app_materno, String fech_nacimiento, Direccion direccion, Contacto contacto) throws ParseException {
        this.id_persona = id_persona;
        this.id_direccion = id_direccion;
        this.id_usuario = id_usuario;
        this.rut = rut;
        this.nombre = nombre;
        this.app_paterno = app_paterno;
        this.app_materno = app_materno;
        
        if (fech_nacimiento != null) {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            this.fech_nacimiento = format.parse(fech_nacimiento);
        } else {
            this.fech_nacimiento = null;
        }
        
        this.direccion = direccion;
        this.contacto = contacto;
    }
    
    public Integer getId_persona() {
        return id_persona;
    }

    public void setId_persona(Integer id_persona) {
        this.id_persona = id_persona;
    }

    public Integer getId_direccion() {
        return id_direccion;
    }

    public void setId_direccion(Integer id_direccion) {
        this.id_direccion = id_direccion;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApp_paterno() {
        return app_paterno;
    }

    public void setApp_paterno(String app_paterno) {
        this.app_paterno = app_paterno;
    }

    public String getApp_materno() {
        return app_materno;
    }

    public void setApp_materno(String app_materno) {
        this.app_materno = app_materno;
    }

    public Date getFech_nacimiento() {
        return fech_nacimiento;
    }

    public void setFech_nacimiento(String fech_nacimiento) throws ParseException {
        if (fech_nacimiento != null) {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            this.fech_nacimiento = format.parse(fech_nacimiento);
        } else {
            this.fech_nacimiento = null;
        }
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public Contacto getContacto() {
        return contacto;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }
    
    @Override
    public String toString() {
        return "id_persona=" + id_persona + ", id_direccion=" + id_direccion + ", id_usuario=" + id_usuario + ", rut=" + rut + ", nombre=" + nombre + ", app_paterno=" + app_paterno + ", app_materno=" + app_materno + ", fech_nacimiento=" + fech_nacimiento;
    }
    
    
}
