package entities;

public class Pais {
    private int id_pais;
    private String nombre;

    public Pais() {
        this.id_pais = 0;
        this.nombre = null;
    }

    public Pais(int id_pais, String nombre) {
        this.id_pais = id_pais;
        this.nombre = nombre;
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
        return "id_pais=" + id_pais + ", nombre=" + nombre;
    }

    
}
