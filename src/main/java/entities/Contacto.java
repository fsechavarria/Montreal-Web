package entities;


public class Contacto {
    
    private Integer id_contacto;
    private Integer id_persona;
    private String desc_contacto;
    private String tipo_contacto;

    public Contacto() {
        this.id_contacto = 0;
        this.id_persona = 0;
        this.desc_contacto = null;
        this.tipo_contacto = null;
    }

    public Contacto(Integer id_contacto, Integer id_persona, String desc_contacto, String tipo_contacto) {
        this.id_contacto = id_contacto;
        this.id_persona = id_persona;
        this.desc_contacto = desc_contacto;
        this.tipo_contacto = tipo_contacto;
    }

    public Integer getId_contacto() {
        return id_contacto;
    }

    public void setId_contacto(Integer id_contacto) {
        this.id_contacto = id_contacto;
    }

    public Integer getId_persona() {
        return id_persona;
    }

    public void setId_persona(Integer id_persona) {
        this.id_persona = id_persona;
    }

    public String getDesc_contacto() {
        return desc_contacto;
    }

    public void setDesc_contacto(String desc_contacto) {
        this.desc_contacto = desc_contacto;
    }

    public String getTipo_contacto() {
        return tipo_contacto;
    }

    public void setTipo_contacto(String tipo_contacto) {
        this.tipo_contacto = tipo_contacto;
    }

    @Override
    public String toString() {
        return "id_contacto=" + id_contacto + ", id_persona=" + id_persona + ", desc_contacto=" + desc_contacto + ", tipo_contacto=" + tipo_contacto;
    }
    
    
}
