package entities;

public class Pais {
    private Integer id_pais;
    private String nombre;

    public Pais() {
        this.id_pais = 0;
        this.nombre = null;
    }

    public Pais(Integer id_pais, String nombre) {
        this.id_pais = id_pais;
        this.nombre = nombre;
    }

    public Integer getId_pais() {
        return id_pais;
    }

    public void setId_pais(Integer id_pais) {
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
