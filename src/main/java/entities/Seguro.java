package entities;

public class Seguro {
    private Integer id_seguro;
    private String desc_seguro;
    private boolean vigente;

    public Seguro() {
        this.id_seguro = 0;
        this.desc_seguro = "";
        this.vigente = false;
    }

    public Seguro(Integer id_seguro, String desc_seguro, Integer vigente) {
        this.id_seguro = id_seguro;
        this.desc_seguro = desc_seguro;
        this.vigente = vigente == 1;
    }

    public Integer getId_seguro() {
        return id_seguro;
    }

    public void setId_seguro(Integer id_seguro) {
        this.id_seguro = id_seguro;
    }

    public String getDesc_seguro() {
        return desc_seguro;
    }

    public void setDesc_seguro(String desc_seguro) {
        this.desc_seguro = desc_seguro;
    }

    public boolean isVigente() {
        return vigente;
    }

    public void setVigente(Integer vigente) {
        this.vigente = vigente == 1;
    }

    @Override
    public String toString() {
        return "id_seguro=" + id_seguro + ", desc_seguro=" + desc_seguro + ", vigente=" + vigente;
    }
    
}
