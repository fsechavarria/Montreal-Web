package entities;


public class Inscripcion_Alumno {
 
    private int id_inscripcion;
    private int id_programa;
    private int id_alumno;
    private int id_curso;

    public Inscripcion_Alumno() {
        this.id_inscripcion = 0;
        this.id_programa = 0;
        this.id_alumno = 0;
        this.id_curso = 0;
    }

    public Inscripcion_Alumno(int id_inscripcion, int id_programa, int id_alumno, int id_curso) {
        this.id_inscripcion = id_inscripcion;
        this.id_programa = id_programa;
        this.id_alumno = id_alumno;
        this.id_curso = id_curso;
    }

    public int getId_inscripcion() {
        return id_inscripcion;
    }

    public void setId_inscripcion(int id_inscripcion) {
        this.id_inscripcion = id_inscripcion;
    }

    public int getId_programa() {
        return id_programa;
    }

    public void setId_programa(int id_programa) {
        this.id_programa = id_programa;
    }

    public int getId_alumno() {
        return id_alumno;
    }

    public void setId_alumno(int id_alumno) {
        this.id_alumno = id_alumno;
    }

    public int getId_curso() {
        return id_curso;
    }

    public void setId_curso(int id_curso) {
        this.id_curso = id_curso;
    }

    @Override
    public String toString() {
        return "id_inscripcion=" + id_inscripcion + ", id_programa=" + id_programa + ", id_alumno=" + id_alumno + ", id_curso=" + id_curso;
    }
    
    
}
