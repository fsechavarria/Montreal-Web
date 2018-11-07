package entities;


public class CEM {
    private Integer id_cem;
    private Integer id_usuario;
    private String nom_centro;
    private Usuario usuario;

    public CEM() {
        this.id_cem = 0;
        this.id_usuario = 0;
        this.nom_centro = "";
        this.usuario = new Usuario();
    }

    public CEM(Integer id_cem, Integer id_usuario, String nom_centro, Usuario usuario) {
        this.id_cem = id_cem;
        this.id_usuario = id_usuario;
        this.nom_centro = nom_centro;
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Integer getId_cem() {
        return id_cem;
    }

    public void setId_cem(Integer id_cem) {
        this.id_cem = id_cem;
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
        return "id_cem=" + id_cem + ", id_usuario=" + id_usuario + ", nom_centro=" + nom_centro;
    }
    
    
}
