package entities;


public class Rol {
    private Integer id_rol;
    private String desc_rol;

    public Rol() {
        this.id_rol = 0;
        this.desc_rol = null;
    }

    public Rol(Integer id_rol, String desc_rol) {
        this.id_rol = id_rol;
        this.desc_rol = desc_rol;
    }

    public Integer getId_rol() {
        return id_rol;
    }

    public void setId_rol(Integer id_rol) {
        this.id_rol = id_rol;
    }

    public String getDesc_rol() {
        return desc_rol;
    }

    public void setDesc_rol(String desc_rol) {
        this.desc_rol = desc_rol;
    }

    @Override
    public String toString() {
        return "id_rol=" + id_rol + ", desc_rol=" + desc_rol;
    }
    
    
}
