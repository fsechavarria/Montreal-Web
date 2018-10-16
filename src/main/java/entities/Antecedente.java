package entities;

/* 
******************************************************************
*****************TODO ENVIAR/RECIVIR ARCHIVOS PDF*****************
******************************************************************
*/
public class Antecedente {
    
    private int id_antecedente;
    private int id_familia;
    private String url_antecedente;
    private String desc_antecedente;

    public Antecedente() {
        this.id_antecedente = 0;
        this.id_familia = 0;
        this.url_antecedente = "";
        this.desc_antecedente = "";
    }

    public Antecedente(int id_antecedente, int id_familia, String url_antecedente, String desc_antecedente) {
        this.id_antecedente = id_antecedente;
        this.id_familia = id_familia;
        this.url_antecedente = url_antecedente;
        this.desc_antecedente = desc_antecedente;
    }

    public int getId_antecedente() {
        return id_antecedente;
    }

    public void setId_antecedente(int id_antecedente) {
        this.id_antecedente = id_antecedente;
    }

    public int getId_familia() {
        return id_familia;
    }

    public void setId_familia(int id_familia) {
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
