package entities;

import java.io.Serializable;

public class Usuario implements Serializable{
    
    private Integer id_usuario;
    private Integer id_rol;
    private String usuario;
    private String contrasena;
    private Persona persona;
    private Rol rol;

    public Usuario () {
        this.id_usuario = 0;
        this.id_rol = 0;
        this.usuario = null;
        this.contrasena = null;
        this.persona = new Persona();
        this.rol = new Rol();
    }

    public Usuario(Integer id_usuario, Integer id_rol, String usuario, String contrasena, Persona persona, Rol rol) {
        this.id_usuario = id_usuario;
        this.id_rol = id_rol;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.persona = persona;
        this.rol = rol;
    }
    
    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Integer getId_rol() {
        return id_rol;
    }

    public void setId_rol(Integer id_rol) {
        this.id_rol = id_rol;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "id_usuario=" + id_usuario + ", id_rol=" + id_rol + ", usuario=" + usuario + ", contrasena=" + contrasena;
    }
    
}
