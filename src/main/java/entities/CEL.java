package entities;

public class CEL {
    private Integer id_cel;
    private Integer id_usuario;
    private String nom_centro;

    public CEL() {
        this.id_cel = 0;
        this.id_usuario = 0;
        this.nom_centro = "";
    }

    public CEL(Integer id_cel, Integer id_usuario, String nom_centro) {
        this.id_cel = id_cel;
        this.id_usuario = id_usuario;
        this.nom_centro = nom_centro;
    }

    public Integer getId_cel() {
        return id_cel;
    }

    public void setId_cel(Integer id_cel) {
        this.id_cel = id_cel;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNom_centro() {
        return nom_centro;
    }

    public void setNom_centro(String nom_centro) {
        this.nom_centro = nom_centro;
    }

    @Override
    public String toString() {
        return "id_cel=" + id_cel + ", id_usuario=" + id_usuario + ", nom_centro=" + nom_centro;
    }
    
    
}
