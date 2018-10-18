package entities;

public class Alumno {
    private Integer id_alumno;
    private Integer id_usuario;

    public Alumno() {
        this.id_alumno = 0;
        this.id_usuario = 0;
    }

    public Alumno(Integer id_alumno, Integer id_usuario) {
        this.id_alumno = id_alumno;
        this.id_usuario = id_usuario;
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

    @Override
    public String toString() {
        return "id_alumno=" + id_alumno + ", id_usuario=" + id_usuario;
    }
    
}
