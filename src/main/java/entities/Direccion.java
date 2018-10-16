package entities;


public class Direccion {
    private int id_direccion;
    private int id_ciudad;
    private String calle;
    private String numeracion;
    private String departamento;

    public Direccion() {
        this.id_direccion = 0;
        this.id_ciudad = 0;
        this.calle = null;
        this.numeracion = null;
        this.departamento = null;
    }

    public Direccion(int id_direccion, int id_ciudad, String calle, String numeracion, String departamento) {
        this.id_direccion = id_direccion;
        this.id_ciudad = id_ciudad;
        this.calle = calle;
        this.numeracion = numeracion;
        this.departamento = departamento;
    }

    public int getId_direccion() {
        return id_direccion;
    }

    public void setId_direccion(int id_direccion) {
        this.id_direccion = id_direccion;
    }

    public int getId_ciudad() {
        return id_ciudad;
    }

    public void setId_ciudad(int id_ciudad) {
        this.id_ciudad = id_ciudad;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumeracion() {
        return numeracion;
    }

    public void setNumeracion(String numeracion) {
        this.numeracion = numeracion;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        return "id_direccion=" + id_direccion + ", id_ciudad=" + id_ciudad + ", calle=" + calle + ", numeracion=" + numeracion + ", departamento=" + departamento;
    }
    
    
}
