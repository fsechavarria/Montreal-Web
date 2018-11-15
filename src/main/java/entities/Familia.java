package entities;


public class Familia {
    
    private Integer id_familia;
    private Integer id_usuario;
    private Integer num_integrantes;
    private String estado;
    private Persona persona;
    private Usuario usuario;

    public Familia() {
        this.id_familia = 0;
        this.id_usuario = 0;
        this.num_integrantes = 0;
        this.estado = "I";
        this.persona = new Persona();
        this.usuario = new Usuario();
    }

    public Familia(Integer id_familia, Integer id_usuario, Integer num_integrantes, String estado, Persona persona, Usuario usuario) {
        this.id_familia = id_familia;
        this.id_usuario = id_usuario;
        this.num_integrantes = num_integrantes;
        this.estado = estado.toUpperCase();
        this.persona = persona;
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Integer getId_familia() {
        return id_familia;
    }

    public void setId_familia(Integer id_familia) {
        this.id_familia = id_familia;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Integer getNum_integrantes() {
        return num_integrantes;
    }

    public void setNum_integrantes(Integer num_integrantes) {
        this.num_integrantes = num_integrantes;
    }

    public String getEstado() {
        return estado.toUpperCase();
    }

    public void setEstado(String estado) {
        this.estado = estado.toUpperCase();
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    @Override
    public String toString() {
        return "id_familia=" + id_familia + ", id_usuario=" + id_usuario + ", num_integrantes=" + num_integrantes + ", estado=" + estado;
    }
    
}
