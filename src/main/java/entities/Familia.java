package entities;


public class Familia {
    
    private Integer id_familia;
    private Integer id_usuario;
    private Integer num_integrantes;
    private char estado;
    private Persona persona;

    public Familia() {
        this.id_familia = 0;
        this.id_usuario = 0;
        this.num_integrantes = 0;
        this.estado = 'I';
        this.persona = new Persona();
    }

    public Familia(Integer id_familia, Integer id_usuario, Integer num_integrantes, char estado, Persona persona) {
        this.id_familia = id_familia;
        this.id_usuario = id_usuario;
        this.num_integrantes = num_integrantes;
        this.estado = estado;
        this.persona = persona;
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

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
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
