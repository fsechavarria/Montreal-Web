package entities;

/* 
******************************************************************
*****************TODO ENVIAR/RECIVIR ARCHIVOS PDF*****************
******************************************************************
*/
public class Antecedente {
    
    private Integer id_antecedente;
    private Integer id_familia;
    private String url_antecedente;
    private String desc_antecedente;

    public Antecedente() {
        this.id_antecedente = 0;
        this.id_familia = 0;
        this.url_antecedente = "";
        this.desc_antecedente = "";
    }

    public Antecedente(Integer id_antecedente, Integer id_familia, String url_antecedente, String desc_antecedente) {
        this.id_antecedente = id_antecedente;
        this.id_familia = id_familia;
        this.url_antecedente = url_antecedente;
        this.desc_antecedente = desc_antecedente;
    }

    public Integer getId_antecedente() {
        return id_antecedente;
    }

    public void setId_antecedente(Integer id_antecedente) {
        this.id_antecedente = id_antecedente;
    }

    public Integer getId_familia() {
        return id_familia;
    }

    public void setId_familia(Integer id_familia) {
        this.id_familia = id_familia;
    }

    public String getUrl_antecedente() {
        return url_antecedente;
    }

    public void setUrl_antecedente(String url_antecedente) {
        this.url_antecedente = url_antecedente;
    }

    public String getDesc_antecedente() {
        return desc_antecedente;
    }

    public void setDesc_antecedente(String desc_antecedente) {
        this.desc_antecedente = desc_antecedente;
    }

    @Override
    public String toString() {
        return "id_antecedente=" + id_antecedente + ", id_familia=" + id_familia + ", url_antecedente=" + url_antecedente + ", desc_antecedente=" + desc_antecedente;
    }
    
}