package entities;


public class CEM {
    private int id_cem;
    private int id_usuario;
    private String nom_centro;

    public CEM() {
        this.id_cem = 0;
        this.id_usuario = 0;
        this.nom_centro = "";
    }

    public CEM(int id_cem, int id_usuario, String nom_centro) {
        this.id_cem = id_cem;
        this.id_usuario = id_usuario;
        this.nom_centro = nom_centro;
    }

    public int getId_cem() {
        return id_cem;
    }

    public void setId_cem(int id_cem) {
        this.id_cem = id_cem;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
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
        return "id_cem=" + id_cem + ", id_usuario=" + id_usuario + ", nom_centro=" + nom_centro;
    }
    
    
}
