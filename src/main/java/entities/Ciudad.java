package entities;

public class Ciudad {
    private Integer id_ciudad;
    private Integer id_pais;
    private String nombre;
    private Pais pais;

    public Ciudad() {
        this.id_ciudad = 0;
        this.id_pais = 0;
        this.nombre = null;
        this.pais = new Pais();
    }

    public Ciudad(Integer id_ciudad, Integer id_pais, String nombre, Pais pais) {
        this.id_ciudad = id_ciudad;
        this.id_pais = id_pais;
        this.nombre = nombre;
        this.pais = pais;
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

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    @Override
    public String toString() {
        return "id_ciudad=" + id_ciudad + ", id_pais=" + id_pais + ", nombre=" + nombre;
    }
    
    
}
