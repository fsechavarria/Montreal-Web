package entities;

public class Ciudad {
    private int id_ciudad;
    private int id_pais;
    private String nombre;

    public Ciudad() {
        this.id_ciudad = 0;
        this.id_pais = 0;
        this.nombre = null;
    }

    public Ciudad(int id_ciudad, int id_pais, String nombre) {
        this.id_ciudad = id_ciudad;
        this.id_pais = id_pais;
        this.nombre = nombre;
    }

    public int getId_ciudad() {
        return id_ciudad;
    }

    public void setId_ciudad(int id_ciudad) {
        this.id_ciudad = id_ciudad;
    }

    public int getId_pais() {
        return id_pais;
    }

    public void setId_pais(int id_pais) {
        this.id_pais = id_pais;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "id_ciudad=" + id_ciudad + ", id_pais=" + id_pais + ", nombre=" + nombre;
    }
    
    
}
