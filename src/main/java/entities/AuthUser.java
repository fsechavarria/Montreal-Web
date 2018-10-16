package entities;

import java.io.Serializable;


public class AuthUser implements Serializable{
    
    private int id;
    private String nombre;
    private String rol;

    public AuthUser() {
        this.id = 0;
        this.nombre = null;
        this.rol = null;
    }

    public AuthUser(int id, String nombre, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.rol = rol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "id=" + id + ", nombre=" + nombre + ", rol=" + rol;
    }
    
}
