package entities;

public class Alumno {
    private int id_alumno;
    private int id_usuario;

    public Alumno() {
        this.id_alumno = 0;
        this.id_usuario = 0;
    }

    public Alumno(int id_alumno, int id_usuario) {
        this.id_alumno = id_alumno;
        this.id_usuario = id_usuario;
    }

    public int getId_alumno() {
        return id_alumno;
    }

    public void setId_alumno(int id_alumno) {
        this.id_alumno = id_alumno;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    @Override
    public String toString() {
        return "id_alumno=" + id_alumno + ", id_usuario=" + id_usuario;
    }
    
}
