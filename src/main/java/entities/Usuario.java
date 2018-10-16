package entities;

public class Usuario{
    
    private int id_usuario;
    private int id_rol;
    private String usuario;
    private String contrasena;

    public Usuario () {
        this.id_usuario = 0;
        this.id_rol = 0;
        this.usuario = null;
        this.contrasena = null;
    }

    public Usuario(int id_usuario, int id_rol, String usuario, String contrasena) {
        this.id_usuario = id_usuario;
        this.id_rol = id_rol;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }
    
    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_rol() {
        return id_rol;
    }

    public void setId_rol(int id_rol) {
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
