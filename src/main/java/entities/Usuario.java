package entities;

import javax.validation.constraints.Size;



public class Usuario{
    
    private Integer id_usuario;
    private Integer id_rol;
    @Size(min=1, message="(*) Debe ingresar un usuario")
    private String usuario;
    @Size(min=1, message="(*) Debe ingresar una contraseña")
    private String contrasena;

    public Usuario () {
        this.id_usuario = 0;
        this.id_rol = 0;
        this.usuario = null;
        this.contrasena = null;
    }

    public Usuario(Integer id_usuario, Integer id_rol, String usuario, String contrasena) {
        this.id_usuario = id_usuario;
        this.id_rol = id_rol;
        this.usuario = usuario;
        this.contrasena = contrasena;
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

    @Override
    public String toString() {
        return "id_usuario=" + id_usuario + ", id_rol=" + id_rol + ", usuario=" + usuario + ", contrasena=" + contrasena;
    }
    
}
