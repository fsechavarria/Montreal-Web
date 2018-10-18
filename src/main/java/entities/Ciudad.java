package entities;

public class Ciudad {
    private Integer id_ciudad;
    private Integer id_pais;
    private String nombre;

    public Ciudad() {
        this.id_ciudad = 0;
        this.id_pais = 0;
        this.nombre = null;
    }

    public Ciudad(Integer id_ciudad, Integer id_pais, String nombre) {
        this.id_ciudad = id_ciudad;
        this.id_pais = id_pais;
        this.nombre = nombre;
    }

    public Integer getId_ciudad() {
        return id_ciudad;
    }

    public void setId_ciudad(Integer id_ciudad) {
        this.id_ciudad = id_ciudad;
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
        return "id_ciudad=" + id_ciudad + ", id_pais=" + id_pais + ", nombre=" + nombre;
    }
    
    
}
