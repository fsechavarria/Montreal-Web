package entities;

public class Alumno {
    private Integer id_alumno;
    private Integer id_usuario;
    private Persona persona;
    private Usuario usuario;
    
    public Alumno() {
        this.id_alumno = 0;
        this.id_usuario = 0;
        this.persona = new Persona();
        this.usuario = new Usuario();
    }

    public Alumno(Integer id_alumno, Integer id_usuario, Persona persona, Usuario usuario) {
        this.id_alumno = id_alumno;
        this.id_usuario = id_usuario;
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public Integer getId_alumno() {
        return id_alumno;
    }

    public void setId_alumno(Integer id_alumno) {
        this.id_alumno = id_alumno;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    @Override
    public String toString() {
        return "id_alumno=" + id_alumno + ", id_usuario=" + id_usuario;
    }
    
}
