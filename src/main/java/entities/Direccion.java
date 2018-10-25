package entities;


public class Direccion {
    private Integer id_direccion;
    private Integer id_ciudad;
    private String calle;
    private String numeracion;
    private String departamento;
    private Ciudad ciudad;

    public Direccion() {
        this.id_direccion = 0;
        this.id_ciudad = 0;
        this.calle = null;
        this.numeracion = null;
        this.departamento = null;
        this.ciudad = new Ciudad();
    }

    public Direccion(Integer id_direccion, Integer id_ciudad, String calle, String numeracion, String departamento, Ciudad ciudad) {
        this.id_direccion = id_direccion;
        this.id_ciudad = id_ciudad;
        this.calle = calle;
        this.numeracion = numeracion;
        this.departamento = departamento;
        this.ciudad = ciudad;
    }

    public Integer getId_direccion() {
        return id_direccion;
    }

    public void setId_direccion(Integer id_direccion) {
        this.id_direccion = id_direccion;
    }

    public Integer getId_ciudad() {
        return id_ciudad;
    }

    public void setId_ciudad(Integer id_ciudad) {
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

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public String toString() {
        return "id_direccion=" + id_direccion + ", id_ciudad=" + id_ciudad + ", calle=" + calle + ", numeracion=" + numeracion + ", departamento=" + departamento;
    }
    
    
}
